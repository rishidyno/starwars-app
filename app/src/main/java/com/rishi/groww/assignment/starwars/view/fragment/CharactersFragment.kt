package com.rishi.groww.assignment.starwars.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import com.rishi.groww.assignment.starwars.R
import com.rishi.groww.assignment.starwars.databinding.FragmentCharactersBinding
import com.rishi.groww.assignment.starwars.model.database.StarWarsDatabaseRepository
import com.rishi.groww.assignment.starwars.model.network.StarWarsNetworkRepository
import com.rishi.groww.assignment.starwars.view.adapters.CharacterPagingAdapter
import com.rishi.groww.assignment.starwars.viewmodel.StarWarsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
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

    private val characterPagingAdapter = CharacterPagingAdapter()




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

//        starWarsViewModel.character.observe(viewLifecycleOwner){
//            characterPagingAdapter.submitData(lifecycle,it)
//        }
//
//        characterPagingAdapter.addLoadStateListener {state->
//            when(state.refresh){
//                is LoadState.Loading -> {
//                    fragmentCharactersBinding.characterProgressBar.visibility = View.VISIBLE
//                }
//                is LoadState.NotLoading -> {
//                    fragmentCharactersBinding.characterProgressBar.visibility = View.GONE
//                }
//                is LoadState.Error->{
//                    fragmentCharactersBinding.characterProgressBar.visibility = View.GONE
//
//                    view.let {
//                        Snackbar.make(it, "Some error occurred", Snackbar.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }

        viewLifecycleOwner.lifecycleScope.launch {
            starWarsViewModel.pager.collectLatest {
                characterPagingAdapter.submitData(it)
            }
        }

        fragmentCharactersBinding.characterRecyclerView.adapter = characterPagingAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentCharactersBinding = null
    }
}
