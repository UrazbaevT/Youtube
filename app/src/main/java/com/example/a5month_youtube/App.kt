package com.example.a5month_youtube

import android.app.Application
import com.example.a5month_youtube.repository.Repository

class App : Application() {

    companion object {
        val repository: Repository by lazy {
            Repository()
        }
    }
}