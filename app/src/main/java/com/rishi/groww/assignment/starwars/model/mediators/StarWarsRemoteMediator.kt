package com.rishi.groww.assignment.starwars.model.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.rishi.groww.assignment.starwars.model.database.StarWarsDatabaseRepository
import com.rishi.groww.assignment.starwars.model.entity.CharacterResponse
import com.rishi.groww.assignment.starwars.model.mappers.fromResultCharactersListToCharacterEntityList
import com.rishi.groww.assignment.starwars.model.network.StarWarsNetworkRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
//
//@OptIn(ExperimentalPagingApi::class)
//class StarWarsRemoteMediator @Inject constructor(
//    private val starWarsNetworkRepository: StarWarsNetworkRepository,
//    private val starWarsDatabaseRepository: StarWarsDatabaseRepository
//) : RemoteMediator<Int, CharacterResponse>() {
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, CharacterResponse>
//    ): MediatorResult {
//        try {
//            // Determine the page number based on loadType and PagingState
//            val page = when (loadType) {
//                LoadType.REFRESH -> 1 // For initial load or refresh
//                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
//                LoadType.APPEND -> {
//                    // Check if there are more pages to load
//                    val lastItem= state.lastItemOrNull()
//                        ?: return MediatorResult.Success(endOfPaginationReached = true)
//
//                }
//            }
//
//            // Fetch data from the network
//            val apiResponse = starWarsNetworkRepository.getAllCharacters(page)
//
//            // Convert API response to entities
//            if (apiResponse.isSuccess) {
//                val characterEntitys = apiResponse.getOrNull()
//
//                starWarsDatabaseRepository.insertOrUpdateCharacters(characterEntitys!!.fromResultCharactersListToCharacterEntityList())
//                // Insert or update the database with the fetched data
//
//                // Return success
//                return MediatorResult.Success(endOfPaginationReached = characterEntitys.next == null)
//
//            } else {
//
//            }
//        } catch (e: IOException) {
//            MediatorResult.Error(e)
//        } catch (e: HttpException) {
//            MediatorResult.Error(e)
//        }
//    }
//}