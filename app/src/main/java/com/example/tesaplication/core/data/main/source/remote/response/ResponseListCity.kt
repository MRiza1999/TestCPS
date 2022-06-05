package com.example.tesaplication.core.data.main.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseListCity(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
