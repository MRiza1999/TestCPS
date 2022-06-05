package com.example.tesaplication.core.data.main.source.remote.network

import com.example.tesaplication.core.data.main.source.remote.request.RequestAddUser
import com.example.tesaplication.core.data.main.source.remote.response.ResponseAddUser
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListCity
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import retrofit2.http.*

interface MainService {

    @GET("v1/accurate/user")
    suspend fun getListUser(
    ):List<ResponseListUser>


    @GET("v1/accurate/city")
    suspend fun getListCity(
    ):List<ResponseListCity>


    @POST("v1/accurate/user")
    suspend fun postAddUser(
        @Body body:RequestAddUser
    ):ResponseAddUser

}