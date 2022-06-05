package com.example.tesaplication.core.domain.main.usecase

import com.example.tesaplication.core.data.main.source.remote.request.RequestAddUser
import com.example.tesaplication.core.data.main.source.remote.response.ResponseAddUser
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.domain.main.model.CityEntityDomain
import com.example.tesaplication.core.domain.main.model.UserEntityDomain
import com.example.tesaplication.core.domain.main.repository.IMainRepository
import com.example.tesaplication.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class MainInteractor(private val mainRepository: IMainRepository): MainUseCase {
    override fun getList(): Flow<Resource<List<UserEntityDomain>>> {
        return mainRepository.getList()
    }

    override fun getListCity(): Flow<Resource<List<CityEntityDomain>>> {
        return mainRepository.getListCity()
    }

    override fun postAddUser(requestAddUser: RequestAddUser): Flow<Resource<ResponseAddUser>> {
        return mainRepository.postAddUser(requestAddUser)
    }

}