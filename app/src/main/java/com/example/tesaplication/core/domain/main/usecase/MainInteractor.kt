package com.example.testingapps.core.domain.main.usecase

import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.domain.main.repository.IMainRepository
import com.example.tesaplication.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class MainInteractor(private val mainRepository: IMainRepository):MainUseCase {
    override fun getList(): Flow<Resource<ArrayList<ResponseListUser>>> {
        return mainRepository.getList()
    }

}