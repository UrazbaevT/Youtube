package com.example.a5month_youtube.di

import com.example.a5month_youtube.repository.Repository
import org.koin.dsl.module

val repoModules = module {
    single { Repository(get()) }
}