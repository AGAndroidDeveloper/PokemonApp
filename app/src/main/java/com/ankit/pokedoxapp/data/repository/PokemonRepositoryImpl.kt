package com.ankit.pokedoxapp.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ankit.pokedoxapp.data.local.database.PokemonDataBase
import com.ankit.pokedoxapp.data.mapper.PokemonMapper.toDomain
import com.ankit.pokedoxapp.data.remote.PokemonRemoteDataSource
import com.ankit.pokedoxapp.data.remotemediator.PokemonRemoteMediator
import com.ankit.pokedoxapp.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val roomDb: PokemonDataBase
) : PokemonRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPaginatedPokemon(): Flow<PagingData<Pokemon>> {
        val pagingSourceFactory = { roomDb.pokemonDao().getAllPokemon() }
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PokemonRemoteMediator(
                pokemonDataSource = pokemonRemoteDataSource,
                pokemonDatabase = roomDb
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            Log.d("TAG", "getPaginatedPokemon: $pagingData")
            pagingData.map { it.toDomain() }
        }
    }



}