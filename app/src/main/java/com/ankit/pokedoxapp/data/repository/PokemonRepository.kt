package com.ankit.pokedoxapp.data.repository

import com.ankit.pokedoxapp.data.remote.PokemonRemoteDataSource
import io.ktor.client.statement.HttpResponse

class PokemonRepository(val remoteDataSource: PokemonRemoteDataSource) {
    suspend fun getAllPokemon(limit: Int, offset: Int) {
        remoteDataSource.getAllPokemon(limit, offset)
    }


    suspend fun getPokemonByName(name: String): HttpResponse {
      return  remoteDataSource.getPokemonByName(name)
    }

}