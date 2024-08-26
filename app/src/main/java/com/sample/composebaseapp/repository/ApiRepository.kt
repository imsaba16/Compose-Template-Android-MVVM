package com.sample.composebaseapp.repository

import com.sample.composebaseapp.di.api.ApiService
import com.sample.composebaseapp.model.BaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.sample.composebaseapp.di.api.Result

class ApiRepository(private val apiService: ApiService) {

    suspend fun getData(): Flow<Result<BaseModel>> {
        return flow {
            val response = apiService.data()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Result.Success(it))
                }
            } else {
                emit(Result.Error(response.message()))
            }
        }.flowOn(Dispatchers.IO)
    }

}
