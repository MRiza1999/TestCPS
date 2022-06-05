package com.example.tesaplication.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.vo.Resource
import com.example.tesaplication.core.domain.main.usecase.MainUseCase
import com.example.tesaplication.core.persistences.mapper.main.MainDataMapper
import com.example.tesaplication.view.main.model.CityEntityPresentation
import com.example.tesaplication.view.main.model.UserEntityPresentation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel (private val mainUseCase: MainUseCase): ViewModel() {

    private val _isLoadingListUser = MutableLiveData<Boolean>()
    private val _dataListUser = MutableLiveData<List<UserEntityPresentation>>()
    private val _isErrorListUser = MutableLiveData<String?>()

    val isLoadingListUser = _isLoadingListUser
    val dataListUser = _dataListUser
    val isErrorListUser = _isErrorListUser

    private val _isLoadingListCity = MutableLiveData<Boolean>()
    private val _dataListCity = MutableLiveData<List<CityEntityPresentation>>()
    private val _isErrorListCity = MutableLiveData<String?>()

    val isLoadingListCity = _isLoadingListCity
    val dataListCity = _dataListCity
    val isErrorListCity = _isErrorListCity

    fun getListUser(){
        viewModelScope.launch {
            mainUseCase.getList()
                .onStart {
                    _isLoadingListUser.postValue(true)
                }
                .onCompletion {
                    _isLoadingListUser.postValue(false)
                }
                .collect { data->
                    when (data) {
                        is Resource.Loading ->
                            _isLoadingListUser.postValue(true)
                        is Resource.Success -> {
                            _isLoadingListUser.postValue(false)
                            _dataListUser.postValue(data.data?.let {
                                MainDataMapper.mapListUserDomainToPresentation(
                                    it
                                )
                            })
                        }
                        is Resource.Error -> {
                            _isLoadingListUser.postValue(false)
                            _isErrorListUser.postValue(data.message)
                        }
                    }
                }
        }
    }

    fun getListCity(){
        viewModelScope.launch {
            mainUseCase.getListCity()
                .onStart {
                    _isLoadingListCity.postValue(true)
                }
                .onCompletion {
                    _isLoadingListCity.postValue(false)
                }
                .collect { data->
                    when (data) {
                        is Resource.Loading ->
                            _isLoadingListCity.postValue(true)
                        is Resource.Success -> {
                            _isLoadingListCity.postValue(false)
                            _dataListCity.postValue(data.data?.let {
                                MainDataMapper.mapListCityDomainToPresentation(
                                    it
                                )
                            })
                        }
                        is Resource.Error -> {
                            _isLoadingListCity.postValue(false)
                            _isErrorListCity.postValue(data.message)
                        }
                    }
                }
        }
    }

}