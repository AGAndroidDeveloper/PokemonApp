package com.ankit.pokedoxapp.domain.repository

import androidx.paging.PagingData
import com.ankit.pokedoxapp.domain.model.Pokemon
import com.ankit.pokedoxapp.domain.model.PokemonInfo
import com.ankit.pokedoxapp.domain.utill.Result
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPaginatedPokemon(): Flow<PagingData<Pokemon>>
    suspend fun getPokemonByName(pokemonName: String): Result<PokemonInfo>
}