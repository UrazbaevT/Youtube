package com.example.a5month_youtube.ui.player

import com.example.a5month_youtube.repository.Repository
import androidx.lifecycle.LiveData
import com.example.a5month_youtube.core.ui.BaseViewModel
import com.example.a5month_youtube.data.remote.model.Videos
import com.example.a5month_youtube.result.Resource

class PlayerViewModel(private val repository: Repository): BaseViewModel() {

    fun getVideo(id: String): LiveData<Resource<Videos>> {
        return repository.getVideo(id)
    }
}