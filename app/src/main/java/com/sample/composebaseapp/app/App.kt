package com.sample.composebaseapp.app

import android.app.Application
import com.sample.composebaseapp.di.module.apiModule
import com.sample.composebaseapp.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(apiModule, appModule)
        }
    }
}