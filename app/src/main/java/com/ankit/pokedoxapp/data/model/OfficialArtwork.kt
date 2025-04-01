package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class OfficialArtwork(
    val front_default: String? = null,
    val front_shiny: String? = null
)