package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GenerationVi(
    val omegaruby_alphasapphire: OmegarubyAlphasapphire,
    val x_y: XY
)