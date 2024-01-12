package com.rishi.groww.assignment.starwars.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rishi.groww.assignment.starwars.model.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class StarWarsRoomDatabase : RoomDatabase() {
    abstract val starWarsDao: StarWarsDao
}
