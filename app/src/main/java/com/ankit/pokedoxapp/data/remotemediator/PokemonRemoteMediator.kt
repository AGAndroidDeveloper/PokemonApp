package com.ankit.pokedoxapp.data.remotemediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ankit.pokedoxapp.data.local.database.PokemonDataBase
import com.ankit.pokedoxapp.data.local.entity.PokemonEntity
import com.ankit.pokedoxapp.data.local.entity.PokemonRemoteKeys
import com.ankit.pokedoxapp.data.remote.PokemonRemoteDataSource

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonDataSource: PokemonRemoteDataSource,
    private val pokemonDatabase: PokemonDataBase,
) : RemoteMediator<Int, PokemonEntity>() {
    private val pokemonDao = pokemonDatabase.pokemonDao()
    private val keysDao = pokemonDatabase.keysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            // Compute offset correctly
            val offset = when (loadType) {
                LoadType.REFRESH -> {
                    // Start from beginning
                    0
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)

                    val remoteKeys = keysDao.remoteKeysByPokemonId(lastItem.id)
                    remoteKeys?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val pageSize = state.config.pageSize
            val response = pokemonDataSource.getAllPokemon(limit = pageSize, offset = offset)

            val pokemons = response.results?.mapNotNull {
                val id = it.url?.trimEnd('/')?.split("/")?.lastOrNull()?.toIntOrNull()
                val imageUrl =
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"

                id?.let { validId ->
                    PokemonEntity(
                        id = validId,
                        name = it.name.orEmpty(),
                        pokemonImageUrl = imageUrl
                    )
                }
            }.orEmpty()

            pokemonDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pokemonDao.deleteAll()
                    keysDao.clearRemoteKeys()
                }

                val prevKey = if (offset == 0) null else offset - pokemons.size
                val nextKey = if (response.next == null) null else offset + pokemons.size

                val keys = pokemons.map {
                    PokemonRemoteKeys(
                        id = it.id,
                        previousKey = prevKey,
                        nextKey = nextKey
                    )
                }

                keysDao.insertAll(keys)
                pokemonDao.insertAll(pokemons)
            }

            MediatorResult.Success(endOfPaginationReached = response.next == null)
        } catch (e: Exception) {
            Log.e("TAG", "loadError: ", e)
            MediatorResult.Error(e)
        }
    }
}
