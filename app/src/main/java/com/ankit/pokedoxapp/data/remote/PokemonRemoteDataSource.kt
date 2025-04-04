package com.ankit.pokedoxapp.data.remote

import com.ankit.pokedoxapp.data.model.PokemonResponse
import com.ankit.pokedoxapp.data.model.PokemonResponseByName
import com.ankit.pokedoxapp.domain.utill.Result
import io.ktor.client.statement.HttpResponse

interface PokemonRemoteDataSource {
    suspend fun getAllPokemon(limit : Int,offset : Int) : PokemonResponse
    suspend fun getPokemonByName(name : String): PokemonResponseByName
}