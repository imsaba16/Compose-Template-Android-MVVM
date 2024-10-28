package com.sample.composebaseapp.di.api

sealed class DataState<out T> {
    data object Loading: DataState<Nothing>()
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val message: String) : DataState<Nothing>()
}
