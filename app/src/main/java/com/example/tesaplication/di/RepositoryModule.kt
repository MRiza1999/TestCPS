package com.example.tesaplication.di

import com.example.tesaplication.core.data.main.source.MainRepository
import com.example.tesaplication.core.data.main.source.local.MainLocalDataSource
import com.example.tesaplication.core.data.main.source.remote.MainRemoteDataSource
import com.example.tesaplication.core.domain.main.repository.IMainRepository
import com.example.tesaplication.util.AppExecutors
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppExecutors() }
    single { MainRemoteDataSource(mainService = get()) }
    single { MainLocalDataSource(userDao = get(), cityDao = get()) }
    single<IMainRepository>{ MainRepository(mainRemoteDataSource = get(), mainLocalDataSource = get(),
        appExecutors = get()) }
}