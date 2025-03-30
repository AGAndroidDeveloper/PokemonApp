package com.ankit.pokedoxapp.data.remotemediator

import android.net.Uri
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
import androidx.core.net.toUri

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonDataSource: PokemonRemoteDataSource,
    private val pokemonDatabase: PokemonDataBase,
) : RemoteMediator<Int, PokemonEntity>() {
    private val TAG = "PokemonRemoteMediator"
    private val pokemonDao = pokemonDatabase.pokemonDao()
    private val keysDao = pokemonDatabase.keysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            Log.e(TAG, "loadType: $loadType")
            // Compute offset based on loadType and parse from API's next/previous URLs
            val (offset, _) = when (loadType) {
                LoadType.REFRESH -> {
                    // Start from the beginning on refresh
                    Pair(null, false)
                }
                LoadType.PREPEND -> {
                    // Prepend is not needed as we only append to the end
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    // Get the last item's remote keys to determine next offset
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    Log.e(TAG, "lastItem: $lastItem")
                    val remoteKeys = keysDao.remoteKeysByPokemonId(lastItem.id)
                    val nextKey = remoteKeys?.nextKey
                    Log.e(TAG, "nextKey: $nextKey")
                    if (nextKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    Pair(nextKey, false)
                }
            }

            // Fetch the next page from the API
            val pageSize = state.config.pageSize
            Log.d(TAG, "Fetching page with offset: $offset, pageSize: $pageSize")
            val response = pokemonDataSource.getAllPokemon(limit = 30, offset = 40)

            // Parse next and previous offsets from the response URLs
            val nextOffset = parseOffsetFromUrl(response.next)
            val prevOffset = parseOffsetFromUrl(response.previous)

            // Map the response to PokemonEntity list
            val pokemons = response.results?.mapNotNull {
                val id = it.url?.trimEnd('/')?.split("/")?.lastOrNull()?.toIntOrNull()
                Log.e(TAG, "id: $id")
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

            // Commit transaction to update database
            pokemonDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pokemonDao.deleteAll()
                    keysDao.clearRemoteKeys()
                }

                // Insert new remote keys for each Pokemon
                val keys = pokemons.map { pokemon ->
                    PokemonRemoteKeys(
                        id = pokemon.id,
                        previousKey = prevOffset,
                        nextKey = nextOffset
                    )
                }
                Log.e(TAG, "keys: $keys")
                Log.e(TAG, "pokemons: $pokemons")
                keysDao.insertAll(keys)
                pokemonDao.insertAll(pokemons)
            }

            // Determine end of pagination
            val endReached = nextOffset == null || pokemons.isEmpty()
            MediatorResult.Success(endOfPaginationReached = endReached)
        } catch (e: Exception) {
            Log.e(TAG, "Error loading data", e)
            MediatorResult.Error(e)
        }
    }

    private fun parseOffsetFromUrl(url: String?): Int? {
        return url?.let {
            val uri = url.toUri()
            val offsetParam = uri.getQueryParameter("offset")
            offsetParam?.toIntOrNull()
        }
    }
}