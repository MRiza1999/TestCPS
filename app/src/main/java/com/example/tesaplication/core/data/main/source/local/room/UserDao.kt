package com.example.tesaplication.core.data.main.source.local.room

import androidx.room.*
import com.example.tesaplication.core.data.main.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM userentity")
    fun getListUserEntity(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListUser(userList: List<UserEntity>)

    @Update
    fun updateListUser(userList: List<UserEntity>)
}