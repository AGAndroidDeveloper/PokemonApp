package com.ankit.pokedoxapp.domain

import androidx.paging.PagingData
import com.ankit.pokedoxapp.data.model.PokemonResponse
import com.ankit.pokedoxapp.data.model.PokemonResponseByName
import com.ankit.pokedoxapp.domain.repository.PokemonRepository
import com.ankit.pokedoxapp.domain.model.Pokemon
import com.ankit.pokedoxapp.domain.model.PokemonInfo
import com.ankit.pokedoxapp.domain.utill.Result
import kotlinx.coroutines.flow.Flow

data class PokemonUseCase(val repository: PokemonRepository) {
    operator fun invoke(): Flow<PagingData<Pokemon>> {
        return repository.getPaginatedPokemon()
    }

    suspend fun getPokemonByName(pokemonName: String): Result<PokemonInfo> {
        return repository.getPokemonByName(pokemonName)
    }

}
