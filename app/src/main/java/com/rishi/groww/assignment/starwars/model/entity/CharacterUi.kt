package com.rishi.groww.assignment.starwars.model.entity

import com.squareup.moshi.Json

data class CharacterUi(
    val birthYear: String,
    val created: String,
    val edited: String,
    val eyeColor: String,
    val films: String?,
    val gender: String?,
    val hairColor: String,
    val height: String,
    val homeWorld: String,
    val mass: String,
    val name: String,
    val skinColor: String?,
    val id: Int,
//    val species: List<String>?,
//    val starShips: List<String>?,
//    val vehicles: List<String>?
)
