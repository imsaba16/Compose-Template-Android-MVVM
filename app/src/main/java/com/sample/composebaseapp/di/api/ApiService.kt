package com.sample.composebaseapp.di.api

import com.sample.composebaseapp.model.BaseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET
    suspend fun data() : Response<BaseModel>
}