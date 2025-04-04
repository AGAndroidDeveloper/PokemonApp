package com.ankit.pokedoxapp.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ankit.pokedoxapp.data.local.database.PokemonDataBase
import com.ankit.pokedoxapp.data.mapper.PokemonMapper.toDomain
import com.ankit.pokedoxapp.data.mapper.PokemonMapper.toPokemonInfoDomain
import com.ankit.pokedoxapp.data.model.PokemonResponseByName
import com.ankit.pokedoxapp.data.remote.PokemonRemoteDataSource
import com.ankit.pokedoxapp.data.remotemediator.PokemonRemoteMediator
import com.ankit.pokedoxapp.domain.model.Pokemon
import com.ankit.pokedoxapp.domain.model.PokemonInfo
import com.ankit.pokedoxapp.domain.repository.PokemonRepository
import com.ankit.pokedoxapp.domain.utill.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.internal.connection.Exchange

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val roomDb: PokemonDataBase,
    private val dispatcher: CoroutineDispatcher
) : PokemonRepository {
    private companion object {
        private const val PAGE_SIZE = 20
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPaginatedPokemon(): Flow<PagingData<Pokemon>> {
        val pagingSourceFactory = { roomDb.pokemonDao().getAllPokemon() }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true,
                prefetchDistance = PAGE_SIZE,
            ),
            remoteMediator = PokemonRemoteMediator(
                pokemonDataSource = pokemonRemoteDataSource,
                pokemonDatabase = roomDb
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            Log.d("TAG", "getPaginatedPokemon: $pagingData.")
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun getPokemonByName(pokemonName: String): Result<PokemonInfo> {
        return withContext(dispatcher) {
            try {
                val data = pokemonRemoteDataSource.getPokemonByName(pokemonName)
                Result.Success(data.toPokemonInfoDomain())
            } catch (e: Exception) {
                Result.Error("Something went wrong error ${e.message}")
            }
        }
    }

}