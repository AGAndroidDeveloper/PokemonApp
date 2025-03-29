package com.ankit.pokedoxapp.application

import android.app.Application
import com.ankit.pokedoxapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokeDoxApplication : Application()  {

    override fun onCreate() {
        super.onCreate()
        starKoin()
    }

    fun starKoin(){
        startKoin {
            androidLogger()
            androidContext(this@PokeDoxApplication)
            modules(appModule)
        }
    }
}