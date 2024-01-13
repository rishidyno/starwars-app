package com.rishi.groww.assignment.starwars.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rishi.groww.assignment.starwars.model.database.StarWarsDatabaseRepository
import com.rishi.groww.assignment.starwars.model.network.StarWarsNetworkRepository
import javax.inject.Inject

//class StarWarsViewModelFactory @Inject constructor(
//    private val appRepository: StarWarsNetworkRepository,
//    private val starWarsDatabaseRepository: StarWarsDatabaseRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        // Check if the provided ViewModel class is AppViewModel
//        if (modelClass.isAssignableFrom(StarWarsViewModel::class.java)) {
//            // If it is, create and return an instance of AppViewModel with the required dependencies
//            return StarWarsViewModel(appRepository, starWarsDatabaseRepository) as T
//        }
//        // If the provided ViewModel class is unknown, throw an IllegalArgumentException
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}