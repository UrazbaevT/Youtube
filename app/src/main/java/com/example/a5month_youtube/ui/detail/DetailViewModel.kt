package com.example.a5month_youtube.ui.detail

import com.example.a5month_youtube.repository.Repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a5month_youtube.core.ui.BaseViewModel
import com.example.a5month_youtube.data.remote.model.Item
import com.example.a5month_youtube.data.remote.model.PlaylistItem
import com.example.a5month_youtube.result.Resource

class DetailViewModel(private val repository: Repository): BaseViewModel() {
    private val mutableDetailId: MutableLiveData<List<String>> = MutableLiveData()
    val liveDetailId: LiveData<List<String>> = mutableDetailId

    fun getDetail(playListId: String): LiveData<Resource<PlaylistItem>>{
        return repository.getPlaylistItem(playListId)
    }

    fun getPlaylistItem(playlistId: String?): LiveData<Resource<PlaylistItem>> {
        return repository.getPlaylistItem(playlistId!!)
    }

    fun getDetailId(data: List<Item>){
        val result = arrayListOf<String>()
        for (i in data) {
            result.add(i.contentDetails.videoId)
        }
        mutableDetailId.value = result
    }
}