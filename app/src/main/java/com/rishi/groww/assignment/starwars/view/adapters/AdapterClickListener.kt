package com.rishi.groww.assignment.starwars.view.adapters

import com.rishi.groww.assignment.starwars.model.entity.CharacterResponse

interface AdapterClickListener {

    fun clickListener(characterResponse: CharacterResponse)
}