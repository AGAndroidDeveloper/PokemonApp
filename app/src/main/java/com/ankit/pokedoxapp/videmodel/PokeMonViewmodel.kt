package com.ankit.pokedoxapp.videmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ankit.pokedoxapp.domain.PokemonUseCase
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.InternalAPI

class PokeMonViewmodel(val useCase: PokemonUseCase) : ViewModel() {
    // TODO WE HAVE TO IMPLEMENT PAGINATION FOR FETCHING POKEMON FROM API

    @OptIn(InternalAPI::class)
    suspend fun getPokemonByName(name: String) {
        val response = useCase.repository.getPokemonByName(name)
        Log.e("response", "httpResponse: ${response.bodyAsText()}")
    }

}