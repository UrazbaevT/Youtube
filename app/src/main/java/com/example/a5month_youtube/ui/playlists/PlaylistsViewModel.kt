package com.example.a5month_youtube.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a5month_youtube.App
import com.example.a5month_youtube.data.remote.model.PlayLists
import com.example.a5month_youtube.core.ui.BaseViewModel
import com.example.a5month_youtube.result.Resource

class PlaylistsViewModel: BaseViewModel() {

    fun playlists(): LiveData<Resource<PlayLists>>{
        return App.repository.getPlayLists()
    }

}