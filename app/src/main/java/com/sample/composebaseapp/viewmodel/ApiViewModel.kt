package com.sample.composebaseapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.composebaseapp.di.api.DataState
import com.sample.composebaseapp.model.BaseModel
import com.sample.composebaseapp.repository.ApiRepository
import com.sample.composebaseapp.utils.apiCall
import com.sample.composebaseapp.utils.liveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ApiViewModel(private val apiRepository: ApiRepository) : ViewModel() {
    private val _data = liveData<BaseModel>()
    val data get() = _data

    fun fetchData() {
        apiCall( { apiRepository.getData() }, _data)
    }

}