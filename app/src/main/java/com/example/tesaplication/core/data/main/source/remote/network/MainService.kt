package com.example.tesaplication.core.data.main.source.remote.network

import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MainService {

    @GET("v1/accurate/user")
    suspend fun getListUser(
    ):ArrayList<ResponseListUser>

}