package com.example.tesaplication.core.data.main.source.local

import com.example.tesaplication.core.data.main.source.local.entity.CityEntity
import com.example.tesaplication.core.data.main.source.local.entity.UserEntity
import com.example.tesaplication.core.data.main.source.local.room.CityDao
import com.example.tesaplication.core.data.main.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class MainLocalDataSource(private val userDao: UserDao,private val cityDao: CityDao) {

    fun getListUser(): Flow<List<UserEntity>> = userDao.getListUserEntity()

    suspend fun insertListUser(list:List<UserEntity>) =
        userDao.insertListUser(list)


    fun getListCity(): Flow<List<CityEntity>> = cityDao.getListCityEntity()

    suspend fun insertListCity(list:List<CityEntity>) =
        cityDao.insertListCity(list)
}