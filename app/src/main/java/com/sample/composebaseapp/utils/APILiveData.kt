package com.sample.composebaseapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.sample.composebaseapp.di.api.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/**
 * Created by Sanjay S on 28-10-2024.
 */


class APILiveData<T> {

    private val _stateFlow = MutableStateFlow<DataState<T>>(DataState.Loading)
    private val stateFlow: StateFlow<DataState<T>> get() = _stateFlow

    private val _loadingState = MutableStateFlow(false)
    private val loadingState: StateFlow<Boolean> get() = _loadingState

    fun postValue(dataState: DataState<T>) {
        _loadingState.value = dataState is DataState.Loading
        _stateFlow.value = dataState
    }

    fun reset() {
        _stateFlow.value = DataState.Loading
        _loadingState.value = false
    }

    @Composable
    fun Observe(
        onLoading: (Boolean) -> Unit = {},
        onError: (String) -> Unit = {},
        onChange: (T) -> Unit,
    ) {
        val stateFlow = remember { stateFlow }
        val loadingState = remember { loadingState }

        LaunchedEffect(stateFlow) {
            try {
                stateFlow
                    .onEach { dataState ->
                        when (dataState) {
                            is DataState.Success -> {
                                onChange(dataState.data)
                                onLoading(false)
                            }
                            is DataState.Error -> {
                                onError(dataState.message)
                                onLoading(false)
                            }
                            is DataState.Loading -> {
                                // Handle loading state within LaunchedEffect
                                // No need to do anything here because it's handled by the `onLoading` lambda
                            }
                        }
                    }
                    .collect()
            } catch (e: Exception) {
                onError("An error occurred while observing data: $e")
            }
        }


        LaunchedEffect(loadingState.value) {
            // Pass the boolean value of loading state to `onLoading`
            onLoading(loadingState.value)
        }
    }
}
