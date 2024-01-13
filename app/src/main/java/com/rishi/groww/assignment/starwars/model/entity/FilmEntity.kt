package com.rishi.groww.assignment.starwars.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "films_table")
data class FilmEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val created: String,
    @ColumnInfo val director: String,
    @ColumnInfo val edited: String,
    @ColumnInfo val producer: String,
    @ColumnInfo val title: String,
    @ColumnInfo val url: String,
    @ColumnInfo val releaseDate: String,
    @ColumnInfo val episodeId: Int,
    @ColumnInfo val openingCrawl: String
) : Parcelable
