package com.example.a5month_youtube.di

import com.example.a5month_youtube.ui.detail.DetailViewModel
import com.example.a5month_youtube.ui.playlists.PlaylistsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel { PlaylistsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}