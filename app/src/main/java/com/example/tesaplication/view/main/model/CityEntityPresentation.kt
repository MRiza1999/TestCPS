package com.example.tesaplication.view.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class CityEntityPresentation (
    val id:Int?=null,
    val name:String? = null,
):Parcelable