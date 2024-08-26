package com.sample.composebaseapp.di.module

import com.sample.composebaseapp.repository.ApiRepository
import com.sample.composebaseapp.viewmodel.ApiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        ApiRepository(get())
    }
    viewModel {
        ApiViewModel(get())
    }
}