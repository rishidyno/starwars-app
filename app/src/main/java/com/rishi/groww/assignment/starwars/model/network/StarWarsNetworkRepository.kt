package com.rishi.groww.assignment.starwars.model.network

import com.rishi.groww.assignment.starwars.exceptions.NoInternetException
import com.rishi.groww.assignment.starwars.model.entity.Character
import com.rishi.groww.assignment.starwars.model.network.StarWarsApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class StarWarsNetworkRepository @Inject constructor(
    private val starWarsApiService: StarWarsApiService,
    private val dispatcher: CoroutineDispatcher
    ){

    suspend fun getAllCharacters(): Result<Character?> {
        return withContext(dispatcher) {
            return@withContext try {
                val response = starWarsApiService.getAllCharacters(4)
                val charactersResponse: Character? = response.body()

                Timber.i("response is %s",response.isSuccessful)
                if (response.isSuccessful) {
                    Result.success(charactersResponse)
                } else {
                    Result.failure(NoInternetException())
                }
            } catch (e: IllegalStateException) {
                Timber.e(e)
                Result.failure(e)
            } catch (e: Exception) {
                Timber.e(e)
                Result.failure(e)
            }
        }
    }

//    suspend fun getAFilm(id:Int): Result<MutableList<Film>?> {
//        return withContext(dispatcher) {
//            return@withContext try {
//                val response = starWarsApiService.getFilmById(id)
//                val filmResponse: MutableList<Film>? = response.body()
//
//                if (response.isSuccessful) {
//                    Result.success(filmResponse)
//                } else {
//                    Result.failure(NoInternetException())
//                }
//            } catch (e: IllegalStateException) {
//                Result.failure(e)
//            } catch (e: Exception) {
//                Result.failure(e)
//            }
//        }
//    }
}