package com.ankit.pokedoxapp.data.remote

import android.util.Log
import com.ankit.pokedoxapp.data.model.PokemonResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import io.ktor.http.parameters

private const val BASE_URL = "https://pokeapi.co/api/v2"

class PokemonRemoteDataSourceImpl(val httpClient: HttpClient) : PokemonRemoteDataSource {
    override suspend fun getAllPokemon(limit: Int, offset: Int): PokemonResponse {
        Log.e("TAG", "getAllPokemonRequest: $limit $offset", )
        val httpResponse   = httpClient.request {
            method = HttpMethod.Get
            url("$BASE_URL/pokemon")
            parameters {
                append("limit", "30")
                append("offset", "40")
            }
        }

        val response  : PokemonResponse = httpResponse.body()
        Log.d("TAG", "getAllPokemonResponse: ${httpResponse.bodyAsText()}")
        return response
    }

    override suspend fun getPokemonByName(name: String): HttpResponse {
        val httpResponse = httpClient.request {
            method = HttpMethod.Get
            url("$BASE_URL/pokemon/$name")
        }

        Log.e("TAG", "getPokemonByNameResponse: $httpResponse", )
        return httpResponse
    }
}