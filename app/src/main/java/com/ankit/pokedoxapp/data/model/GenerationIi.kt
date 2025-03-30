package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable

data class GenerationIi(
    val crystal: Crystal,
    val gold: Gold,
    val silver: Silver
)