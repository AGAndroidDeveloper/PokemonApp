package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UltraSunUltraMoon(
    val front_default: String,
    val front_female: String? = null,
    val front_shiny: String,
    val front_shiny_female: String? = null
)