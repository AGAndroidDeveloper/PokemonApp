package com.ankit.pokedoxapp.data.repository

import androidx.paging.PagingData
import com.ankit.pokedoxapp.data.remote.PokemonRemoteDataSource
import com.ankit.pokedoxapp.domain.Pokemon
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPaginatedPokemon() : Flow<PagingData<Pokemon>>
}