package com.example.tesaplication.core.data.main.source.remote

import com.example.tesaplication.core.ApiResponse
import com.example.tesaplication.core.data.main.source.remote.network.MainService
import com.example.tesaplication.core.data.main.source.remote.request.RequestAddUser
import com.example.tesaplication.core.data.main.source.remote.response.ResponseAddUser
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListCity
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class MainRemoteDataSource(private val mainService: MainService) {

    suspend fun getListUser(): Flow<ApiResponse<List<ResponseListUser>>> {
        return flow {
            try {
                val response = mainService.getListUser()
                emit(ApiResponse.Success(response))
            }catch (e:HttpException){
                if (e.code() in 400..499){
                    emit(ApiResponse.Error(e.code().toString()))
                }else if (e.code()==500){
                    emit(ApiResponse.Error(e.code().toString()))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getListCity(): Flow<ApiResponse<List<ResponseListCity>>> {
        return flow {
            try {
                val response = mainService.getListCity()
                emit(ApiResponse.Success(response))
            }catch (e:HttpException){
                if (e.code() in 400..499){
                    emit(ApiResponse.Error(e.code().toString()))
                }else if (e.code()==500){
                    emit(ApiResponse.Error(e.code().toString()))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun postAddUser(requestAddUser: RequestAddUser): Flow<ApiResponse<ResponseAddUser>> {
        return flow {
            try {
                val response = mainService.postAddUser(requestAddUser)
                emit(ApiResponse.Success(response))
            }catch (e:HttpException){
                if (e.code() in 400..499){
                    emit(ApiResponse.Error(e.code().toString()))
                }else if (e.code()==500){
                    emit(ApiResponse.Error(e.code().toString()))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


}