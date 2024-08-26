package com.sample.composebaseapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.composebaseapp.di.api.Result
import com.sample.composebaseapp.model.BaseModel
import com.sample.composebaseapp.repository.ApiRepository
import kotlinx.coroutines.launch

class ApiViewModel(private val apiRepository: ApiRepository) : ViewModel() {
    private val _data = MutableLiveData<Result<BaseModel>>()
    val data: LiveData<Result<BaseModel>> = _data

    fun fetchData() {
        viewModelScope.launch {
            apiRepository.getData().collect {
                _data.value = it
            }
        }
    }

}