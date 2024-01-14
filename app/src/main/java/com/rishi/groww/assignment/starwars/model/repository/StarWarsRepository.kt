package com.rishi.groww.assignment.starwars.model.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.rishi.groww.assignment.starwars.model.entity.CharacterResponse
import com.rishi.groww.assignment.starwars.model.entity.ResultCharacters
import com.rishi.groww.assignment.starwars.model.network.StarWarsNetworkRepository
import com.rishi.groww.assignment.starwars.model.paging.StarWarsPagingSource

class StarWarsRepository(private val starWarsNetworkRepository: StarWarsNetworkRepository) {

    fun getAllCharactersStream(): LiveData<PagingData<ResultCharacters>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            prefetchDistance = 7,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            StarWarsPagingSource(starWarsNetworkRepository = starWarsNetworkRepository)
        }
    ).liveData

}