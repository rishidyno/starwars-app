package com.rishi.groww.assignment.starwars.model.database

import androidx.annotation.WorkerThread
import com.rishi.groww.assignment.starwars.model.entity.CharacterEntity
import javax.inject.Inject

class StarWarsDatabaseRepository (private val starWarsDao: StarWarsDao) {

//    val pagingSource = starWarsDao.pagingSource()

    @WorkerThread
    suspend fun insertOrUpdateCharacters(characterEntity: List<CharacterEntity>) {
        starWarsDao.insertOrUpdateCharacters(characterEntity)
    }

}