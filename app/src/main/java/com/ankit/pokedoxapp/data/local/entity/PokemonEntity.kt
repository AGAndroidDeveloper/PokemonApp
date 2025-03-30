package com.ankit.pokedoxapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val pokemonImageUrl: String
)