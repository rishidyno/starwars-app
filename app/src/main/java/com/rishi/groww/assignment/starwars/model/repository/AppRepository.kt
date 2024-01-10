package com.rishi.groww.assignment.starwars.model.repository

import com.rishi.groww.assignment.starwars.exceptions.NoInternetException
import com.rishi.groww.assignment.starwars.model.entity.Character
import com.rishi.groww.assignment.starwars.model.entity.Film
import com.rishi.groww.assignment.starwars.model.network.StarWarsApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val starWarsApiService: StarWarsApiService,
    private val dispatcher: CoroutineDispatcher
    ){

    suspend fun getAllCharacters(): Result<MutableList<Character>?> {
        return withContext(dispatcher) {
            return@withContext try {
                val response = starWarsApiService.getAllCharacters()
                val charactersResponse: MutableList<Character>? = response.body()

                if (response.isSuccessful) {
                    Result.success(charactersResponse)
                } else {
                    Result.failure(NoInternetException())
                }
            } catch (e: IllegalStateException) {
                Result.failure(e)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getAFilm(id:Int): Result<MutableList<Film>?> {
        return withContext(dispatcher) {
            return@withContext try {
                val response = starWarsApiService.getFilmById(id)
                val filmResponse: MutableList<Film>? = response.body()

                if (response.isSuccessful) {
                    Result.success(filmResponse)
                } else {
                    Result.failure(NoInternetException())
                }
            } catch (e: IllegalStateException) {
                Result.failure(e)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}