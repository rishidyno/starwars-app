package com.rishi.groww.assignment.starwars.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rishi.groww.assignment.starwars.databinding.ItemCharacterLayoutBinding
import com.rishi.groww.assignment.starwars.model.entity.CharacterEntity
import com.rishi.groww.assignment.starwars.model.entity.CharacterResponse
import com.rishi.groww.assignment.starwars.model.entity.ResultCharacters
import timber.log.Timber

class CharacterPagingAdapter :
    PagingDataAdapter<CharacterEntity, CharacterPagingAdapter.CharactersViewHolder>(DIFF_UTIL) {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<CharacterEntity>() {
            override fun areItemsTheSame(
                oldItem: CharacterEntity,
                newItem: CharacterEntity
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CharacterEntity,
                newItem: CharacterEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    inner class CharactersViewHolder(val itemCharacterLayoutBinding: ItemCharacterLayoutBinding) :
        RecyclerView.ViewHolder(itemCharacterLayoutBinding.root){
    }


    override fun onBindViewHolder(
        holder: CharacterPagingAdapter.CharactersViewHolder,
        position: Int
    ) {

        val character = getItem(position)

        holder.itemCharacterLayoutBinding.name.text = character!!.name
        holder.itemCharacterLayoutBinding.birthYear.text = character.birthYear
        holder.itemCharacterLayoutBinding.gender.text = character.gender
        holder.itemCharacterLayoutBinding.eyeColor.text = character.eyeColor
        holder.itemCharacterLayoutBinding.birthYear.text = character.birthYear
        holder.itemCharacterLayoutBinding.hairColor.text = character.hairColor
        holder.itemCharacterLayoutBinding.homeWorld.text = character.homeWorld
        holder.itemCharacterLayoutBinding.created.text = character.created
        holder.itemCharacterLayoutBinding.edited.text = character.edited
        holder.itemCharacterLayoutBinding.height.text = character.height
        holder.itemCharacterLayoutBinding.mass.text = character.mass

        Timber.i("hello baby", character.films.toString())
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterPagingAdapter.CharactersViewHolder {
        val itemCharacterLayoutBinding: ItemCharacterLayoutBinding =
            ItemCharacterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(itemCharacterLayoutBinding)
    }

}