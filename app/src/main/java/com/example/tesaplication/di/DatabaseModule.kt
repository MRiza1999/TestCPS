package com.example.tesaplication.di

import androidx.room.Room
import com.example.tesaplication.db.Database
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    factory {
        get<Database>().userDao()
    }

    single {
        val passhrase:ByteArray= SQLiteDatabase.getBytes("testCPS".toCharArray())
        val factory = SupportFactory(passhrase)
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,"testCPS.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

}