package com.rishi.groww.assignment.starwars.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rishi.groww.assignment.starwars.model.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class StarWarsRoomDatabase: RoomDatabase() {

    abstract fun starWarsDao(): StarWarsDao


    companion object {
        // Singleton prevents multiple instances of database opening at the ame time.
        @Volatile
        private var INSTANCE: StarWarsRoomDatabase? = null

        fun getDatabase(context: Context): StarWarsRoomDatabase {
            // if the INSTANCE is not null, then return it, if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StarWarsRoomDatabase::class.java,
                    "message_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
