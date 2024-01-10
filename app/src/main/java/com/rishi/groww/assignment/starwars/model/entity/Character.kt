package com.rishi.groww.assignment.starwars.model.entity

import com.squareup.moshi.Json

data class Character(
    @Json(name = "birth_year")
    val birthYear: String,
    val created: String,
    val edited: String,
    @Json(name = "eye_color")
    val eyeColor: String,
    val films: List<String>,
    val gender: String,
    @Json(name = "hair_color")
    val hairColor: String,
    val height: String,
    @Json(name = "homeworld")
    val homeWorld: String,
    val mass: String,
    val name: String,
    @Json(name = "skin_color")
    val skinColor: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)