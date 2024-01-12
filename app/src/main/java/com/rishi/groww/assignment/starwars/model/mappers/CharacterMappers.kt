package com.rishi.groww.assignment.starwars.model.mappers

import com.rishi.groww.assignment.starwars.model.entity.CharacterEntity
import com.rishi.groww.assignment.starwars.model.entity.CharacterUi
import com.rishi.groww.assignment.starwars.model.entity.ResultCharacters

fun ResultCharacters.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = (url[url.length - 1]) - '0',
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
        films = films?.joinToString("") { it[it.length - 1].toString() },
        skinColor = skinColor
    )
}

fun CharacterEntity.roCharacterUi(): CharacterUi {
    return CharacterUi(
        id = id,
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
        skinColor = skinColor,
        films = films
    )
}