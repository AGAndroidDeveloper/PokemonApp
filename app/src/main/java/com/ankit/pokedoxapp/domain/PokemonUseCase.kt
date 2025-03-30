package com.ankit.pokedoxapp.domain

import androidx.paging.PagingData
import com.ankit.pokedoxapp.data.model.PokemonResponse
import com.ankit.pokedoxapp.data.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

data class PokemonUseCase(val repository: PokemonRepository) {
    operator fun invoke(): Flow<PagingData<Pokemon>> {
        return repository.getPaginatedPokemon()
    }

}
