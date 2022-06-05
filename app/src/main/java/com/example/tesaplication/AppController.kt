package com.example.tesaplication

import android.app.Application
import com.example.tesaplication.di.networkModule
import com.example.tesaplication.di.repositoryModule
import com.example.tesaplication.di.usecaseModule
import com.example.tesaplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class AppController:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AppController)
            koin.loadModules(listOf(
                networkModule,
                repositoryModule,
                usecaseModule,
                viewModelModule
            ))
        }

    }


    fun baseURL() : String = "https://627e360ab75a25d3f3b37d5a.mockapi.io/api/"

}