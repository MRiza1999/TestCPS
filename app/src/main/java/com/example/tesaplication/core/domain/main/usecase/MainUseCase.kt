package com.example.tesaplication.core.domain.main.usecase

import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.domain.main.model.CityEntityDomain
import com.example.tesaplication.core.domain.main.model.UserEntityDomain
import com.example.tesaplication.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    fun getList(): Flow<Resource<List<UserEntityDomain>>>
    fun getListCity():Flow<Resource<List<CityEntityDomain>>>
}