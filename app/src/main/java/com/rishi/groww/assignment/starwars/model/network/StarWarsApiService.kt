package com.rishi.groww.assignment.starwars.model.network

import com.rishi.groww.assignment.starwars.model.entity.CharacterResponse
import com.rishi.groww.assignment.starwars.model.entity.Film
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApiService {

    @GET("people")
    suspend fun getAllCharacters(
        @Query("page") page:Int
    ): Response<CharacterResponse>

    @GET("people")
    suspend fun getAllStarWarsCharacters(
        @Query("page") page:Int
    ): Response<CharacterResponse>


    @GET("films/{id}/")
    suspend fun getFilmById(
        @Path("id") filmId: Int)
    : Response<Film>
}
