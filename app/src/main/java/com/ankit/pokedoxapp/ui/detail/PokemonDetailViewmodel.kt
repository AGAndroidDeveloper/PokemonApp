package com.ankit.pokedoxapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankit.pokedoxapp.data.model.PokemonResponseByName
import com.ankit.pokedoxapp.domain.PokemonUseCase
import com.ankit.pokedoxapp.domain.utill.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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