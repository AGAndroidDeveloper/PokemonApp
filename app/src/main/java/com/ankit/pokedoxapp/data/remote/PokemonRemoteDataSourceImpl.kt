package com.ankit.pokedoxapp.data.remote

import android.util.Log
import com.ankit.pokedoxapp.data.model.PokemonResponse
import com.ankit.pokedoxapp.data.model.PokemonResponseByName
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.HttpMethod
import io.ktor.http.parameters
import com.ankit.pokedoxapp.domain.utill.Result

private const val BASE_URL = "https://pokeapi.co/api/v2"

class PokemonRemoteDataSourceImpl(val httpClient: HttpClient) : PokemonRemoteDataSource {
    override suspend fun getAllPokemon(limit: Int, offset: Int): PokemonResponse {
        Log.e("TAG", "getAllPokemonRequest: $limit $offset")
        val httpResponse = httpClient.get("$BASE_URL/pokemon?limit=$limit&offset=$offset")
        Log.d("TAG", "Fetching: ${BASE_URL}/pokemon?limit=$limit&offset=$offset")
//        val httpResponse   = httpClient.request {
//            method = HttpMethod.Get
//            url("$BASE_URL/pokemon")
//            Log.d("TAG", "Fetching: ${BASE_URL}/pokemon?limit=$limit&offset=$offset")
//            parameters {
//                append("limit", "$limit")
//                append("offset", "$offset")
//            }
//        }

        val response: PokemonResponse = httpResponse.body()
        Log.d("TAG", "getAllPokemonResponse: $response")
        return response
    }

    override suspend fun getPokemonByName(name: String): PokemonResponseByName {
//        val httpResponse = httpClient.request {
//            method = HttpMethod.Get
//            url("$BASE_URL/pokemon/$name")
//        }
        val url = "$BASE_URL/pokemon/$name"
        Log.d("TAG", "Fetching: $url")
        val httpResponse = httpClient.get(url)
        val response: PokemonResponseByName = httpResponse.body()
        Log.e("TAG", "getPokemonByNameResponse: $httpResponse")
        return response
    }

}