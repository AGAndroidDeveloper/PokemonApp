package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Type(
    @SerialName("slot")  val slot: Int,
    @SerialName("type")  val type: TypeX
)