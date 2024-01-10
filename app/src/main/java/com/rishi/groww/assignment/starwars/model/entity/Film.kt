package com.rishi.groww.assignment.starwars.model.entity

import com.squareup.moshi.Json

data class Film(
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    @Json(name = "episode_id")
    val episodeId: Int,
    @Json(name = "opening_crawl")
    val openingCrawl: String,
    val planets: List<String>,
    val producer: String,
    @Json(name = "release_date")
    val releaseDate: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<String>
)