package com.rishi.groww.assignment.starwars.model.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rishi.groww.assignment.starwars.model.entity.CharacterEntity

@Dao
interface StarWarsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharactersToDatabase(characters: List<CharacterEntity>)

    @Query("SELECT * from characters_table")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM characters_table")
    suspend fun clearAll()

}