package com.ankit.pokedoxapp.data.remote

import android.util.Log
import com.ankit.pokedoxapp.data.model.Pokemon
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import io.ktor.http.parameters
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://pokeapi.co/api/v2"

class PokemonRemoteDataSourceImpl(val httpClient: HttpClient) : PokemonRemoteDataSource {
    override suspend fun getAllPokemon(limit: Int, offset: Int): Pokemon {
        val httpResponse = httpClient.request {
            method = HttpMethod.Get
            url("$BASE_URL/pokemon")
            parameters {
                append("limit", limit.toString())
                append("offset", offset.toString())
            }
        }

        val json = httpResponse.bodyAsText()
        Log.d("TAG", "getAllPokemonResponse: $json")
        return Json.decodeFromString(Pokemon,json)
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