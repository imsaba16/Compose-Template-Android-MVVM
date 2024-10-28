package com.sample.composebaseapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.sample.composebaseapp.viewmodel.ApiViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(innerPadding: PaddingValues, apiViewModel: ApiViewModel = koinViewModel()) {
    LaunchedEffect(Unit) {
        apiViewModel.fetchData()
    }
    apiViewModel.data.Observe {

    }
}