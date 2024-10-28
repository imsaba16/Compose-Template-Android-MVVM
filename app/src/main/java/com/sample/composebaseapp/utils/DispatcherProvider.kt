package com.sample.composebaseapp.utils

/**
 * Created by Sanjay S on 28-10-2024.
 */

import kotlinx.coroutines.Dispatchers
interface DispatcherProvider {
    val main
        get() = Dispatchers.Main
    val default
        get() = Dispatchers.Default
    val io
        get() = Dispatchers.IO
    val unconfined
        get() = Dispatchers.Unconfined
}
class DefaultDispatcherProvider : DispatcherProvider