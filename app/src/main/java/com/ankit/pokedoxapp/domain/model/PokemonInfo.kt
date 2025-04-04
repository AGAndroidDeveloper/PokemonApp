package com.ankit.pokedoxapp.domain.model

import androidx.compose.ui.graphics.Color
import com.ankit.pokedoxapp.data.model.Species
import com.ankit.pokedoxapp.data.model.States
import com.ankit.pokedoxapp.data.model.Type
import kotlinx.serialization.SerialName

data class PokemonInfo(
    val id: Int? = null,
    val name: String? = null,
    val species: Species? = Species(),
    val stats: List<DStates>? = null,
    val types: List<Type>? = null,
    val weight: Int? = null,
    val height: Int? = null,
    val trackColor: Color? = null,
    val progressColor: Color? = null
)

data class DStates(val baseStat: Int? = null, val effort: Int? = null, val name: String? = null)