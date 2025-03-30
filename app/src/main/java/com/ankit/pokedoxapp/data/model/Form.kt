package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable

data class Form(
    val name: String,
    val url: String
)