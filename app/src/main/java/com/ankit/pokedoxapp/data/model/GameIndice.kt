package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable

data class GameIndice(
    val game_index: Int,
    val version: Version
)