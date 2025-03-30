package com.ankit.pokedoxapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ankit.pokedoxapp.data.local.dao.KeysDao
import com.ankit.pokedoxapp.data.local.dao.PokemonDao
import com.ankit.pokedoxapp.data.local.entity.PokemonEntity
import com.ankit.pokedoxapp.data.local.entity.PokemonRemoteKeys

@Database(
    entities = [PokemonEntity::class, PokemonRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class PokemonDataBase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun keysDao(): KeysDao
}