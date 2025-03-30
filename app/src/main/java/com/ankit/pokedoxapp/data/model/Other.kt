package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    val official_artwork: OfficialArtwork,
    val showdown: Showdown
)