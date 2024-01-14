package com.rishi.groww.assignment.starwars.model.database

import androidx.annotation.WorkerThread
import androidx.paging.PagingSource
import com.rishi.groww.assignment.starwars.model.entity.CharacterEntity
import com.rishi.groww.assignment.starwars.model.entity.CharacterRemoteKeys

class StarWarsDatabaseRepository(private val starWarsDao: StarWarsDao) {

//    val pagingSource = starWarsDao.pagingSource()

    @WorkerThread
    suspend fun insertOrUpdateCharacters(characterEntity: List<CharacterEntity>) {
        starWarsDao.insertOrUpdateCharacters(characterEntity)
    }

    @WorkerThread
    suspend fun getAllCharacterRemoteKey(characterRemoteKeys: Int): CharacterRemoteKeys? {
        return starWarsDao.getCharacterRemoteKey(characterRemoteKeys)
    }

    @WorkerThread
    suspend fun deleteAllCharacterRemoteKeys() {
        starWarsDao.deleteCharacterRemoteKeys()
    }

    @WorkerThread
    suspend fun deleteAllCharacters() {
        starWarsDao.deleteAllCharacters()
    }

    @WorkerThread
    suspend fun insertAllCharacterRemoteKey(remoteKeyList: List<CharacterRemoteKeys>) {
        starWarsDao.insertAllCharacterRemoteKey(remoteKeyList)
    }

    @WorkerThread
    suspend fun getAllCharacterFromDatabase(): PagingSource<Int, CharacterEntity> =
        starWarsDao.getAllCharacters()

}