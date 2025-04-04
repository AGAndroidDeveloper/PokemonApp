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
import com.ankit.pokedoxapp.data.model.PokemonResponse
import com.ankit.pokedoxapp.domain.utill.Result
import kotlin.collections.firstOrNull
import kotlin.collections.isNotEmpty

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
            val offset = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 0
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.previousKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                  val id =   state.lastItemOrNull()?.id
                    Log.e(TAG, " LoadType.APPEND id: $id")
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                    if (nextKey == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    nextKey
                }
            }

            val pageSize = state.config.pageSize
            Log.d(TAG, "Fetching page with offset: $offset, pageSize: $pageSize")
            val response = pokemonDataSource.getAllPokemon(limit = pageSize, offset = offset)
            val endOfPaginationReached =
                response.count == offset - pageSize || response.results?.isEmpty() == true

            val prevPage = if (offset == 0) null else offset - pageSize
            val nextPage = if (endOfPaginationReached) null else offset + pageSize
            // Parse next and previous offsets from the response URLs
//            val nextOffset = parseOffsetFromUrl(response.next)
//            val prevOffset = parseOffsetFromUrl(response.previous)

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

            pokemonDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pokemonDao.deleteAll()
                    keysDao.clearRemoteKeys()
                }

                val keys = pokemons.map { pokemon ->
                    PokemonRemoteKeys(
                        id = pokemon.id,
                        previousKey = prevPage,
                        nextKey = nextPage
                    )
                }

                Log.e(TAG, "keys: $keys ::  pokemons: $pokemons")
                Log.e(TAG, "")
                keysDao.insertAll(keys)
                pokemonDao.insertAll(pokemons)
            }

            // Determine end of pagination
            val endReached = nextPage == null || pokemons.isEmpty()
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

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PokemonEntity>
    ): PokemonRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                keysDao.remoteKeysByPokemonId(id)
            }
        }
    }


    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PokemonEntity>
    ): PokemonRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { pokemonEntity ->
                keysDao.remoteKeysByPokemonId(pokemonEntity.id)
            }
    }


    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PokemonEntity>
    ): PokemonRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { pokemonEntity ->
                keysDao.remoteKeysByPokemonId(pokemonEntity.id)
            }
    }
}