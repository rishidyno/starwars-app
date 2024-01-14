package com.rishi.groww.assignment.starwars.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rishi.groww.assignment.starwars.model.database.StarWarsDatabaseRepository
import com.rishi.groww.assignment.starwars.model.entity.CharacterResponse
import com.rishi.groww.assignment.starwars.model.entity.ResultCharacters
import com.rishi.groww.assignment.starwars.model.mediators.StarWarsRemoteMediator
import com.rishi.groww.assignment.starwars.model.network.StarWarsNetworkRepository
import com.rishi.groww.assignment.starwars.model.repository.StarWarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class StarWarsViewModel @Inject constructor(
    private val starWarsRepository: StarWarsRepository,
    private val starWarsNetworkRepository: StarWarsNetworkRepository,
    private val starWarsDatabaseRepository: StarWarsDatabaseRepository
) : ViewModel() {

//    val character: LiveData<PagingData<ResultCharacters>> =
//        starWarsRepository.getAllCharactersStream()

    val pager = Pager(
        PagingConfig(
            pageSize = 10,
            prefetchDistance = 5,
        ),
        remoteMediator = StarWarsRemoteMediator(
            starWarsNetworkRepository = starWarsNetworkRepository,
            starWarsDatabaseRepository = starWarsDatabaseRepository
        )
    ) {
        runBlocking {
            starWarsDatabaseRepository.getAllCharacterFromDatabase()
        }
    }.flow
}
