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
import com.rishi.groww.assignment.starwars.model.entity.CharacterResponse
import com.rishi.groww.assignment.starwars.model.entity.ResultCharacters

class CharacterPagingAdapter :
    PagingDataAdapter<ResultCharacters, CharacterPagingAdapter.CharactersViewHolder>(DIFF_UTIL) {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<ResultCharacters>() {
            override fun areItemsTheSame(
                oldItem: ResultCharacters,
                newItem: ResultCharacters
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ResultCharacters,
                newItem: ResultCharacters
            ): Boolean {
                return oldItem.url == newItem.url
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