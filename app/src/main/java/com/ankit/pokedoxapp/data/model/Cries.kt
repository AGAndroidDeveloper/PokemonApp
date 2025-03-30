package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable

data class Cries(
    val latest: String,
    val legacy: String
)