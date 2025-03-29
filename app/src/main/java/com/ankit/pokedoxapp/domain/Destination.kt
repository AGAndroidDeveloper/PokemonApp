package com.ankit.pokedoxapp.domain

import kotlinx.serialization.Serializable

sealed class Destination {

    @Serializable
    data object Home : Destination()

    @Serializable
    data class Detail(val index: Int) : Destination()
}