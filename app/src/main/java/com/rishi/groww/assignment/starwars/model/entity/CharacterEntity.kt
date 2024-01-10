package com.rishi.groww.assignment.starwars.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
// Define the Table name
@Entity(tableName = "star_wars_table")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val height: String,
    @ColumnInfo val gender: String?,
    // Specifies the name of the column in the table if you want it to be different from the name of the member variable.
    @ColumnInfo(name = "birth_year") val birthYear: String
) : Parcelable