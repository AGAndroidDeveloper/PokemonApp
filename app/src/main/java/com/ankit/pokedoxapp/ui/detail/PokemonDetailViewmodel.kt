package com.ankit.pokedoxapp.ui.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankit.pokedoxapp.data.model.PokemonResponseByName
import com.ankit.pokedoxapp.domain.PokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.ankit.pokedoxapp.domain.utill.Result
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonDetailViewmodel(val useCase: PokemonUseCase) : ViewModel() {
    private val _pokemonState = MutableStateFlow<Result<PokemonResponseByName>>(Result.Idle)
    val pokemonState: StateFlow<Result<PokemonResponseByName>> = _pokemonState.asStateFlow()

    companion object {
        private const val TAG = "PokemonDetailViewmodel"
    }

    fun getPokemonByName(name: String) {
        viewModelScope.launch {
            _pokemonState.value = Result.Loading
            try {
                _pokemonState.value = Result.Success(useCase.getPokemonByName(name))
            } catch (e: Exception) {
                _pokemonState.value = Result.Error(errorMessage = "${e.cause}")
            }
        }
    }
}