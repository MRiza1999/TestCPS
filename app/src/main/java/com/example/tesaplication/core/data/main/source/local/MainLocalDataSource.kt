package com.example.tesaplication.core.data.main.source.local

import com.example.tesaplication.core.data.main.source.local.entity.UserEntity
import com.example.tesaplication.core.data.main.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class MainLocalDataSource(private val userDao: UserDao) {

    fun getListUser(): Flow<List<UserEntity>> = userDao.getListUserEntity()

    suspend fun insertListUser(list:List<UserEntity>) =
        userDao.insertListKecelakaan(list)
}