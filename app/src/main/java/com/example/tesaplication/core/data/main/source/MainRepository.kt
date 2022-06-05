package com.example.tesaplication.core.data.main.source

import com.example.tesaplication.core.ApiResponse
import com.example.tesaplication.core.data.NetworkBoundResource
import com.example.tesaplication.core.data.main.source.local.MainLocalDataSource
import com.example.tesaplication.core.data.main.source.remote.MainRemoteDataSource
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.domain.main.model.UserEntityDomain
import com.example.tesaplication.core.domain.main.repository.IMainRepository
import com.example.tesaplication.core.persistences.mapper.main.MainDataMapper
import com.example.tesaplication.core.vo.Resource
import com.example.tesaplication.util.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MainRepository(private val mainRemoteDataSource: MainRemoteDataSource,
                     private val mainLocalDataSource: MainLocalDataSource,
                     private val appExecutors: AppExecutors) : IMainRepository {

    /*override fun getList(): Flow<Resource<List<ResponseListUser>>>  = flow {
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
    }*/


    override fun getList(): Flow<Resource<List<UserEntityDomain>>>  =
        object:
            NetworkBoundResource<List<UserEntityDomain>, List<ResponseListUser?>>(){
            override fun loadFromDB(): Flow<List<UserEntityDomain>> {
                return mainLocalDataSource.getListUser().map {
                    MainDataMapper.mapListUser(it)
                }
            }

            override fun shouldFetch(data: List<UserEntityDomain>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResponseListUser?>>> =
                mainRemoteDataSource.getListUser()

            override suspend fun saveCallResult(data: List<ResponseListUser?>) {
                val movieList=MainDataMapper.mapListUserResponeToEntity(data)
                mainLocalDataSource.insertListUser(movieList)
            }
        }.asFlow()

}