package com.rishi.groww.assignment.starwars.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.rishi.groww.assignment.starwars.R
import com.rishi.groww.assignment.starwars.databinding.FragmentCharactersBinding
import com.rishi.groww.assignment.starwars.model.database.StarWarsDatabaseRepository
import com.rishi.groww.assignment.starwars.model.repository.AppRepository
import com.rishi.groww.assignment.starwars.viewmodel.StarWarsViewModel
import com.rishi.groww.assignment.starwars.viewmodel.StarWarsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _fragmentCharactersBinding: FragmentCharactersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val fragmentCharactersBinding get() = _fragmentCharactersBinding!!

    private lateinit var navController: NavController

    @Inject
    lateinit var appRepository: AppRepository

    @Inject
    lateinit var starWarsDatabaseRepository: StarWarsDatabaseRepository

    private val starWarsViewModel: StarWarsViewModel by viewModels {
        StarWarsViewModelFactory(appRepository,starWarsDatabaseRepository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragmentCharactersBinding = FragmentCharactersBinding.inflate(inflater, container, false)
        return fragmentCharactersBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        getAllCharacters()

        starWarsViewModel.character.observe(viewLifecycleOwner){ characterList ->
            Log.i("character", characterList.toString())
        }

        fragmentCharactersBinding.tv.setOnClickListener {
            navController.navigate(R.id.action_CharactersFragment_to_FilmsFragment)
        }

    }

    private fun getAllCharacters() {
        // Coroutine to fetch all messages from the repository
        CoroutineScope(Dispatchers.Main).launch {
            starWarsViewModel.getAllCharacters()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentCharactersBinding = null
    }
}