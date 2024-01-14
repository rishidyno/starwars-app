package com.rishi.groww.assignment.starwars.model.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.google.android.material.circularreveal.CircularRevealHelper.Strategy
import com.rishi.groww.assignment.starwars.model.entity.CharacterResponse
import com.rishi.groww.assignment.starwars.model.entity.CharacterEntity
import com.rishi.groww.assignment.starwars.model.entity.CharacterRemoteKeys
import com.rishi.groww.assignment.starwars.model.entity.ResultCharacters

@Dao
interface StarWarsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateCharacters(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters_table")
    suspend fun deleteAllCharacters()

    @Query("SELECT * FROM characters_table")
    fun getAllCharacters():PagingSource<Int,CharacterEntity>

    @Upsert
    suspend fun insertAllCharacterRemoteKey(characterRemoteKeys: List<CharacterRemoteKeys>)

    @Query("SELECT * FROM character_remote_keys WHERE id = :id")
    suspend fun getCharacterRemoteKey(id:Int):CharacterRemoteKeys?

    @Query("DELETE FROM character_remote_keys")
    suspend fun deleteCharacterRemoteKeys()
}
