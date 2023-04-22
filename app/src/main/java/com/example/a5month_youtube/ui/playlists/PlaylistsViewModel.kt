package com.example.a5month_youtube.ui.playlists

import com.example.a5month_youtube.repository.Repository
import androidx.lifecycle.LiveData
import com.example.a5month_youtube.data.remote.model.PlayLists
import com.example.a5month_youtube.core.ui.BaseViewModel
import com.example.a5month_youtube.result.Resource

class PlaylistsViewModel(private val repository: Repository): BaseViewModel() {

    fun playlists(): LiveData<Resource<PlayLists>>{
        return repository.getPlaylists()
    }

}