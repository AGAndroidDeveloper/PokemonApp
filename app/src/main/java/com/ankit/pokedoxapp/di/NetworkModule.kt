package com.ankit.pokedoxapp.di

import com.ankit.pokedoxapp.data.remote.PokemonRemoteDataSource
import com.ankit.pokedoxapp.data.remote.PokemonRemoteDataSourceImpl
import com.ankit.pokedoxapp.data.repository.PokemonRepository
import com.ankit.pokedoxapp.domain.PokemonUseCase
import com.ankit.pokedoxapp.videmodel.PokeMonViewmodel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

import kotlinx.serialization.serializer
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object NetworkModule {
    private const val BASE_URL = "https://pokeapi.co/api/v2"
}

fun provideKtorClient() = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

fun provideHttpClient(): HttpClient {
    return HttpClient(Android) {
        // Install the Logging feature
        install(Logging) {
            level = LogLevel.ALL  // Log all details: request, response, body, etc.
            logger = Logger.DEFAULT  // You can use a custom logger, or the default
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}

val appModule = module {
//    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
//    viewModelOf(::UserViewModel)
    single {
        provideHttpClient()
    }

    single<PokemonRemoteDataSource> { PokemonRemoteDataSourceImpl(get()) }
    single { PokemonRepository(get()) }
    single { PokemonUseCase(get()) }
    viewModel { PokeMonViewmodel(get()) }
}
