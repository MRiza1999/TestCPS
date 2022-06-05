package com.example.tesaplication.core.data.main.source.local.room

import androidx.room.*
import com.example.tesaplication.core.data.main.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM userentity")
    fun getListUserEntity(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListKecelakaan(userList: List<UserEntity>)

    @Update
    fun updateListKecelakaan(userList: List<UserEntity>)
}