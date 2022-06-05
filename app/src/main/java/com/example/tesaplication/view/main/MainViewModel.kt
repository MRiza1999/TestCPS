package com.example.tesaplication.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.vo.Resource
import com.example.testingapps.core.domain.main.usecase.MainUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel (private val mainUseCase: MainUseCase): ViewModel() {

    private val _isLoadingListUser = MutableLiveData<Boolean>()
    private val _dataListUser = MutableLiveData<ArrayList<ResponseListUser>?>()
    private val _isErrorListUser = MutableLiveData<String?>()

    val isLoadingListUser = _isLoadingListUser
    val dataListUser = _dataListUser
    val isErrorListUser = _isErrorListUser


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
                            _dataListUser.postValue(data.data)
                        }
                        is Resource.Error -> {
                            _isLoadingListUser.postValue(false)
                            _isErrorListUser.postValue(data.message)
                        }
                    }
                }
        }
    }
}