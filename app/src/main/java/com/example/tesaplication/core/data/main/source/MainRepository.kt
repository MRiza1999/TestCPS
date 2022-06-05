package com.example.tesaplication.core.data.main.source

import com.example.tesaplication.core.ApiResponse
import com.example.tesaplication.core.data.main.source.remote.MainRemoteDataSource
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.domain.main.repository.IMainRepository
import com.example.tesaplication.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class MainRepository(private val mainRemoteDataSource: MainRemoteDataSource) : IMainRepository {

    override fun getList(): Flow<Resource<ArrayList<ResponseListUser>>>  = flow {
        emit(Resource.Loading())
        when(val apiResponse = mainRemoteDataSource.getListUser().first()){
            is ApiResponse.Success->{
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty->{
                emit(Resource.Error("Empty List"))
            }
            is ApiResponse.Error ->{
                emit(Resource.Error(apiResponse.errorMessage.toString()))
            }
        }
    }

}