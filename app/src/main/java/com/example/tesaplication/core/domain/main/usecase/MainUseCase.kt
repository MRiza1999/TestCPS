package com.example.testingapps.core.domain.main.usecase

import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    fun getList(): Flow<Resource<ArrayList<ResponseListUser>>>
}