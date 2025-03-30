package com.ankit.pokedoxapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class PokemonRemoteKeys(
    @PrimaryKey val id: Int,
    val previousKey: Int?,
    val nextKey: Int?
)