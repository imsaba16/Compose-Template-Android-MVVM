package com.sample.composebaseapp.repository

import com.sample.composebaseapp.di.api.ApiService
import com.sample.composebaseapp.di.api.DataState
import com.sample.composebaseapp.model.BaseModel
import com.sample.composebaseapp.utils.safeCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApiRepository(private val apiService: ApiService) {

    suspend fun getData(): Flow<DataState<BaseModel>> {
        return safeCall { apiService.data() }
    }

}
