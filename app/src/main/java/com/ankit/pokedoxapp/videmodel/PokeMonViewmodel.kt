package com.ankit.pokedoxapp.videmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ankit.pokedoxapp.domain.PokemonUseCase
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.flow.stateIn

class PokeMonViewmodel(val useCase: PokemonUseCase) : ViewModel() {
    private val TAG = "PokeMonViewmodel"

    @OptIn(InternalAPI::class)
    suspend fun getPokemonByName(name: String) {

    }

    val response = useCase.invoke().cachedIn(viewModelScope)

}