package com.example.tesaplication.core.data.main.source

import com.example.tesaplication.core.ApiResponse
import com.example.tesaplication.core.data.NetworkBoundResource
import com.example.tesaplication.core.data.main.source.local.MainLocalDataSource
import com.example.tesaplication.core.data.main.source.remote.MainRemoteDataSource
import com.example.tesaplication.core.data.main.source.remote.request.RequestAddUser
import com.example.tesaplication.core.data.main.source.remote.response.ResponseAddUser
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListCity
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.domain.main.model.CityEntityDomain
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
                val list=MainDataMapper.mapListUserResponeToEntity(data)
                mainLocalDataSource.insertListUser(list)
            }
        }.asFlow()

    override fun getListCity(): Flow<Resource<List<CityEntityDomain>>> =
        object:
            NetworkBoundResource<List<CityEntityDomain>, List<ResponseListCity?>>(){
            override fun loadFromDB(): Flow<List<CityEntityDomain>> {
                return mainLocalDataSource.getListCity().map {
                    MainDataMapper.mapListCity(it)
                }
            }

            override fun shouldFetch(data: List<CityEntityDomain>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResponseListCity?>>> =
                mainRemoteDataSource.getListCity()

            override suspend fun saveCallResult(data: List<ResponseListCity?>) {
                val list=MainDataMapper.mapListCityResponeToEntity(data)
                mainLocalDataSource.insertListCity(list)
            }
        }.asFlow()

    override fun postAddUser(requestAddUser: RequestAddUser): Flow<Resource<ResponseAddUser>> = flow {
        emit(Resource.Loading())
        when(val apiResponse = mainRemoteDataSource.postAddUser(requestAddUser).first()){
            is ApiResponse.Success->{
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty->{
                emit(Resource.Error("Error"))
            }
            is ApiResponse.Error->{
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

}