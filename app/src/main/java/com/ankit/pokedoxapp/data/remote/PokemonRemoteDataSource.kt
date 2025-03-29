package com.ankit.pokedoxapp.data.remote

import com.ankit.pokedoxapp.data.model.Pokemon
import io.ktor.client.statement.HttpResponse

interface PokemonRemoteDataSource {
    suspend fun getAllPokemon(limit : Int,offset : Int) : Pokemon
    suspend fun getPokemonByName(name : String): HttpResponse
}