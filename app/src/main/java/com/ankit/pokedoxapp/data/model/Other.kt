package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.*
import kotlinx.serialization.Serializable

@Serializable
data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    @SerialName("official-artwork" ) var official_artwork : OfficialArtwork? = OfficialArtwork(),
    val showdown: Showdown
)