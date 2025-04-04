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
import kotlinx.coroutines.launch

class PokeMonViewmodel(val useCase: PokemonUseCase) : ViewModel() {
    private val TAG = "PokeMonViewmodel"

    init {
//        viewModelScope.launch {
//           val value =  response.stateIn(viewModelScope).value
//            Log.w(TAG, "init: $value")
//        }
    }
    val response = useCase.invoke().cachedIn(viewModelScope)

}