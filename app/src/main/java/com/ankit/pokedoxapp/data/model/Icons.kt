package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Icons(
    val front_default: String,
    val front_female: String? = null
)