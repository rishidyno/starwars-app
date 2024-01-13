package com.rishi.groww.assignment.starwars.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.rishi.groww.assignment.starwars.model.database.StarWarsDatabaseRepository
import com.rishi.groww.assignment.starwars.model.entity.CharacterResponse
import com.rishi.groww.assignment.starwars.model.entity.ResultCharacters
import com.rishi.groww.assignment.starwars.model.network.StarWarsNetworkRepository
import com.rishi.groww.assignment.starwars.model.repository.StarWarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StarWarsViewModel @Inject constructor(
    private val starWarsRepository: StarWarsRepository
) : ViewModel() {

    val character: LiveData<PagingData<ResultCharacters>> =
        starWarsRepository.getAllCharactersStream()
}
