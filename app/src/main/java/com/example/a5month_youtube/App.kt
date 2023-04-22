package com.example.a5month_youtube

import android.app.Application
import com.example.a5month_youtube.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(koinModules)
        }
    }
}