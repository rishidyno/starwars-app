package com.rishi.groww.assignment.starwars.model.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.rishi.groww.assignment.starwars.model.database.StarWarsDatabaseRepository
import com.rishi.groww.assignment.starwars.model.entity.CharacterEntity
import com.rishi.groww.assignment.starwars.model.network.StarWarsNetworkRepository
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class StarWarsRemoteMediator @Inject constructor(
    private val starWarsNetworkRepository: StarWarsNetworkRepository,
    private val starWarsDatabaseRepository: StarWarsDatabaseRepository
): RemoteMediator<Int, CharacterEntity>(){
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}