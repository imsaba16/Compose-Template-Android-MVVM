package com.sample.composebaseapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.composebaseapp.di.api.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by Sanjay S on 28-10-2024.
 */

suspend fun <T> safeCall(
    apiCall: suspend () -> Response<T>
): Flow<DataState<T>> {
    return flow {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(DataState.Success(it))
            } ?: emit(DataState.Error("Empty response body"))
        } else {
            emit(DataState.Error(response.message()))
        }
    }.flowOn(Dispatchers.IO)
}

fun exceptionHandler(throwable: Throwable): DataState.Error {
    return when (throwable) {
        is SocketTimeoutException -> return  DataState.Error("Request timed out, please try again.")
        is IOException -> return DataState.Error("Network error, please check your connection.")
        is HttpException ->return DataState.Error("Server error: ${throwable.message}")
        else -> return DataState.Error("An unexpected error occurred: ${throwable.message}")
    }
}

fun <T> liveData() = APILiveData<T>()
fun <T> ViewModel.apiCall(
    call: suspend () -> Flow<DataState<T>>,
    liveData: APILiveData<T>,
) {
    viewModelScope.launch(Dispatchers.IO) {
        call().collect { dataState -> liveData.postValue(dataState) }
    }
}