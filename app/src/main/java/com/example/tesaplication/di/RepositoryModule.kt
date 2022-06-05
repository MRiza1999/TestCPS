package com.example.tesaplication.di

import com.example.tesaplication.core.data.main.source.MainRepository
import com.example.tesaplication.core.data.main.source.remote.MainRemoteDataSource
import com.example.tesaplication.core.domain.main.repository.IMainRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MainRemoteDataSource(mainService = get()) }
    single<IMainRepository>{ MainRepository(mainRemoteDataSource = get()) }
}