package com.rishi.groww.assignment.starwars.model.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.rishi.groww.assignment.starwars.model.database.StarWarsDatabaseRepository
import com.rishi.groww.assignment.starwars.model.entity.CharacterEntity
import com.rishi.groww.assignment.starwars.model.entity.CharacterRemoteKeys
import com.rishi.groww.assignment.starwars.model.mappers.toCharacterEntity
import com.rishi.groww.assignment.starwars.model.network.StarWarsNetworkRepository
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.io.InvalidObjectException
import java.sql.Time
import javax.inject.Inject


const val PAGE_STARTING_INDEX: Int = 1

@OptIn(ExperimentalPagingApi::class)
class StarWarsRemoteMediator @Inject constructor(
    private val starWarsNetworkRepository: StarWarsNetworkRepository,
    private val starWarsDatabaseRepository: StarWarsDatabaseRepository
) : RemoteMediator<Int, CharacterEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.REFRESH -> {
                    val lastItem = getClosestRemoteKey(state)
                    lastItem?.nextKey?.minus(1) ?: PAGE_STARTING_INDEX
                }

                LoadType.APPEND -> {
                    val lastItem =
                        getLastRemoteKey(state)
                            ?: throw InvalidObjectException("Invalid Object Exception")
                    lastItem.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }


            // Fetch data from the network
            val apiResponse = starWarsNetworkRepository.getAllCharacters(page)



            if (apiResponse.isSuccess) {
                val characterDTO = apiResponse.getOrNull()

                if (loadType == LoadType.REFRESH) {
                    runBlocking {
                        starWarsDatabaseRepository.deleteAllCharacters()
                        starWarsDatabaseRepository.deleteAllCharacterRemoteKeys()
                    }


                    val nextKey = extractLastNumber(characterDTO?.next)
                    val prevKey = extractLastNumber(characterDTO?.previous)

                    val remoteKeyList = characterDTO?.results?.map {
                        val id: Int = extractLastNumber(it.url)!!
                        CharacterRemoteKeys(
                            id = id,
                            nextKey = nextKey,
                            prevKey = prevKey
                        )
                    }

                    val characterEntities = characterDTO?.results?.map {
                        it.toCharacterEntity()
                    }!!

                    Timber.i("characterEntities", characterEntities.toString())

                    runBlocking {
                        starWarsDatabaseRepository.insertOrUpdateCharacters(characterEntities)
                        if (remoteKeyList != null) {
                            starWarsDatabaseRepository.insertAllCharacterRemoteKey(remoteKeyList)
                        }
                    }
                    MediatorResult.Success(endOfPaginationReached = characterDTO.next == null)
                }


                // Return success
                MediatorResult.Success(endOfPaginationReached = characterDTO?.next == null)

            } else {
                MediatorResult.Success(endOfPaginationReached = true)
            }

        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private fun getClosestRemoteKey(state: PagingState<Int, CharacterEntity>): CharacterRemoteKeys? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                runBlocking {
                    starWarsDatabaseRepository.getAllCharacterRemoteKey(it.id)
                }
            }
        }
    }

    private fun getLastRemoteKey(state: PagingState<Int, CharacterEntity>): CharacterRemoteKeys? {
        return state.lastItemOrNull()?.let {
            runBlocking {
                starWarsDatabaseRepository.getAllCharacterRemoteKey(it.id)
            }
        }
    }

    private fun extractLastNumber(url: String?): Int? {
        if (url.isNullOrBlank()) {
            return null
        }

        val regex: Regex = if (url[url.length - 1] == '/') "/(\\d+)/$".toRegex() else "page=(\\d+)$".toRegex()

        val matchResult = regex.find(url)
        val x: Int? = matchResult?.groupValues?.get(1)?.toIntOrNull()
        Timber.i(x.toString())
        return matchResult?.groupValues?.get(1)?.toIntOrNull()
    }
}
