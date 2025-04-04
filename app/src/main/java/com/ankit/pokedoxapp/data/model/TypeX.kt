package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeX(
    @SerialName("name")  val name: String,
    @SerialName("url")   val url: String
)