package com.ankit.pokedoxapp.domain.utill

sealed class Result<out T> {
    data object Idle : Result<Nothing>()
    data object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val errorMessage: String, val data: T? = null) : Result<T>()
}
