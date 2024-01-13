package com.rishi.groww.assignment.starwars.model.entity

import androidx.room.Entity

@Entity(tableName = "character_remote_keys")
data class CharacterRemoteKeys(
    val label: String,
    val nextKey: String?)