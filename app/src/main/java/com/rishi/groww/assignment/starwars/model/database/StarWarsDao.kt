package com.rishi.groww.assignment.starwars.model.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.rishi.groww.assignment.starwars.model.entity.CharacterResponse
import com.rishi.groww.assignment.starwars.model.entity.CharacterEntity

@Dao
interface StarWarsDao {

    @Upsert
    suspend fun insertOrUpdateCharacters(characters: List<CharacterEntity>)

//    @Query("SELECT * from characters_table")
//    fun pagingSource(): PagingSource<Int, CharacterResponse>

    @Query("DELETE FROM characters_table")
    suspend fun clearAll()

}