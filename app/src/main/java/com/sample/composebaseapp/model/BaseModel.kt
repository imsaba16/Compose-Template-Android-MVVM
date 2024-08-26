package com.sample.composebaseapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseModel(val name: String)
