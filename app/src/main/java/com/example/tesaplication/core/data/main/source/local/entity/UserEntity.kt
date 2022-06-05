package com.example.tesaplication.core.data.main.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userentity")
class UserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "address")
    var address: String?,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "phoneNumber")
    var phoneNumber: String?,

    @ColumnInfo(name = "city")
    var city: String?,

    @ColumnInfo(name = "statusUpload")
    var statusUpload:Boolean = true

)