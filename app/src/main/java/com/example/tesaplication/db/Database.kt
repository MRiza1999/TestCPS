package com.example.tesaplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tesaplication.core.data.main.source.local.entity.UserEntity
import com.example.tesaplication.core.data.main.source.local.room.UserDao

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database: RoomDatabase() {
    abstract fun userDao(): UserDao
}