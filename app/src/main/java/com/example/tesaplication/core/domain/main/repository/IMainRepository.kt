package com.example.tesaplication.core.domain.main.repository

import com.example.tesaplication.core.data.main.source.remote.request.RequestAddUser
import com.example.tesaplication.core.data.main.source.remote.response.ResponseAddUser
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.domain.main.model.CityEntityDomain
import com.example.tesaplication.core.domain.main.model.UserEntityDomain
import com.example.tesaplication.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    fun getList():Flow<Resource<List<UserEntityDomain>>>
    fun getListCity():Flow<Resource<List<CityEntityDomain>>>
    fun postAddUser(requestAddUser: RequestAddUser):Flow<Resource<ResponseAddUser>>
}