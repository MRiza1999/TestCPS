package com.example.tesaplication.di

import com.example.testingapps.core.domain.main.usecase.MainInteractor
import com.example.testingapps.core.domain.main.usecase.MainUseCase
import org.koin.dsl.module

val usecaseModule = module {
    factory<MainUseCase> { MainInteractor(get()) }
}