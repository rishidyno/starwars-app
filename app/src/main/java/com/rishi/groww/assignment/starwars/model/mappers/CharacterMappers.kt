package com.rishi.groww.assignment.starwars.model.mappers

import com.rishi.groww.assignment.starwars.model.entity.CharacterResponse
import com.rishi.groww.assignment.starwars.model.entity.CharacterEntity
import com.rishi.groww.assignment.starwars.model.entity.CharacterUi
import com.rishi.groww.assignment.starwars.model.entity.ResultCharacters

fun ResultCharacters.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = extractLastNumber(url)!!,
        name = name,
        birthYear = birthYear,
        height = height,
        gender = gender,
        created = created,
        edited = edited,
        eyeColor = eyeColor,
        hairColor = hairColor,
        homeWorld = homeWorld,
        mass = mass,
        films = films?.joinToString("") { it[it.length - 2].toString() }, // doing this because we only have 6 films in the backend database
        skinColor = skinColor
    )
}

fun extractLastNumber(url: String): Int? {
    val regex = "/(\\d+)/$".toRegex()
    val matchResult = regex.find(url)
    return matchResult?.groupValues?.get(1)?.toInt()
}

//fun CharacterEntity.roCharacterUi(): CharacterUi {
//    return CharacterUi(
//        id = id,
//        name = name,
//        birthYear = birthYear,
//        height = height,
//        gender = gender,
//        created = created,
//        edited = edited,
//        eyeColor = eyeColor,
//        hairColor = hairColor,
//        homeWorld = homeWorld,
//        mass = mass,
//        skinColor = skinColor,
//        films = films
//    )
//}
