package com.example.tesaplication.core.domain.main.repository

import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    fun getList():Flow<Resource<ArrayList<ResponseListUser>>>
}