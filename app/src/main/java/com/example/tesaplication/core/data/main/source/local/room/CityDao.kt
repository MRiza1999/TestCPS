package com.example.tesaplication.core.data.main.source.local.room

import androidx.room.*
import com.example.tesaplication.core.data.main.source.local.entity.CityEntity
import com.example.tesaplication.core.data.main.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query("SELECT * FROM cityentity")
    fun getListCityEntity(): Flow<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListCity(cityList: List<CityEntity>)

    @Update
    fun updateListCity(cityList: List<CityEntity>)
}