package com.example.a5month_youtube.di

import com.example.a5month_youtube.core.network.networkModule
import com.example.a5month_youtube.data.remote.remoteDataSource

val koinModules = listOf(
    repoModules,
    viewModule,
    remoteDataSource,
    networkModule
)
