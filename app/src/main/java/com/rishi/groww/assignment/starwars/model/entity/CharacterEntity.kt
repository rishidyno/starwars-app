package com.rishi.groww.assignment.starwars.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "characters_table")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val height: String,
    @ColumnInfo val gender: String?,
    @ColumnInfo val birthYear: String,
    @ColumnInfo val mass: String,
    @ColumnInfo val hairColor: String,
    @ColumnInfo val skinColor: String,
    @ColumnInfo val eyeColor: String,
    @ColumnInfo val homeWorld: String,
    @ColumnInfo val created: String,
    @ColumnInfo val edited: String,
    @ColumnInfo val films: String?
) : Parcelable