package com.rishi.groww.assignment.starwars.model.entity

import com.squareup.moshi.Json

data class ResultFilm(
    val created: String,
    val director: String,
    val edited: String,
    val producer: String,
    val title: String,
    val url: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "episode_id")
    val episodeId: Int,
    @Json(name = "opening_crawl")
    val openingCrawl: String,
//    val species: List<String>?,
//    val starships: List<String>?,
//    val vehicles: List<String>?
//    val characterResponses: List<String>,
//    val planets: List<String>?,
)


data class Film(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ResultFilm>
)

