package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable

data class GenerationI(
    val red_blue: RedBlue,
    val yellow: Yellow
)