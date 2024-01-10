package com.rishi.groww.assignment.starwars.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rishi.groww.assignment.starwars.model.database.StarWarsDatabaseRepository
import com.rishi.groww.assignment.starwars.model.entity.Character
import com.rishi.groww.assignment.starwars.model.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StarWarsViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val starWarsRepository: StarWarsDatabaseRepository
) : ViewModel() {

    private var _characters = MutableLiveData<MutableList<Character>>()
    val character: LiveData<MutableList<Character>> get() = _characters
    suspend fun getAllCharacters() {
//        _isLoading.postValue(true)
        withContext(Dispatchers.IO) {
            // Fetch messages from the repository
            val allMessages = appRepository.getAllCharacters()
            allMessages.getOrNull()?.let {
                // Update messages LiveData with the fetched messages
                _characters.postValue(it)
            }
//            _isLoading.postValue(false)
        }
    }
}