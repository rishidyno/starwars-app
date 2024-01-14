package com.rishi.groww.assignment.starwars.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_remote_keys")
data class CharacterRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val prevKey: Int?,
    val nextKey: Int?
)