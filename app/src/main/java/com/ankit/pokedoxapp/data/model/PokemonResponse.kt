package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonData>? = null
)

@Serializable
data class PokemonData(
    val name: String? = null,
    val url: String? = null
)

