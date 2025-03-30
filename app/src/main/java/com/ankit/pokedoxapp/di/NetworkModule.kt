package com.ankit.pokedoxapp.di

import android.app.Application
import androidx.room.Room
import com.ankit.pokedoxapp.data.local.database.PokemonDataBase
import com.ankit.pokedoxapp.data.remote.PokemonRemoteDataSource
import com.ankit.pokedoxapp.data.remote.PokemonRemoteDataSourceImpl
import com.ankit.pokedoxapp.data.repository.PokemonRepository
import com.ankit.pokedoxapp.data.repository.PokemonRepositoryImpl
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
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

object NetworkModule {
    private const val BASE_URL = "https://pokeapi.co/api/v2"
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

        engine {
            connectTimeout = 100_000
            socketTimeout = 100_000
        }
    }
}

val appModule = module {
    single { provideHttpClient() } // Make sure this function is correct

   // single { androidApplication() } // Provide Application instance

    // Room Database
    single {
        Room.databaseBuilder(
            androidContext(), // Ensure proper context
            PokemonDataBase::class.java,
            "PokemonDataBase"
        ).build()
    }

    // Data Sources
    single<PokemonRemoteDataSource> { PokemonRemoteDataSourceImpl(get()) }

    // Repository
    single<PokemonRepository> { PokemonRepositoryImpl(get(), get()) }

    // UseCase
    single { PokemonUseCase(get()) }

    // ViewModel
    viewModel { PokeMonViewmodel(get()) }
}

