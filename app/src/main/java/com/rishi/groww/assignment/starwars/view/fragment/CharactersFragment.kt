package com.rishi.groww.assignment.starwars.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.rishi.groww.assignment.starwars.R
import com.rishi.groww.assignment.starwars.databinding.FragmentCharactersBinding
import com.rishi.groww.assignment.starwars.model.database.StarWarsDatabaseRepository
import com.rishi.groww.assignment.starwars.model.network.StarWarsNetworkRepository
import com.rishi.groww.assignment.starwars.viewmodel.StarWarsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _fragmentCharactersBinding: FragmentCharactersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val fragmentCharactersBinding get() = _fragmentCharactersBinding!!

    private lateinit var navController: NavController

    @Inject
    lateinit var appRepository: StarWarsNetworkRepository

    @Inject
    lateinit var starWarsDatabaseRepository: StarWarsDatabaseRepository

    private lateinit var starWarsViewModel: StarWarsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragmentCharactersBinding = FragmentCharactersBinding.inflate(inflater, container, false)
        starWarsViewModel= ViewModelProvider(this)[StarWarsViewModel::class.java]

        return fragmentCharactersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        getAllCharacters()

        starWarsViewModel.character.observe(viewLifecycleOwner) { charactersList ->
            val character = charactersList.results
            Timber.tag("character").i(character.toString())
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
