package com.example.a5month_youtube.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a5month_youtube.App
import com.example.a5month_youtube.core.ui.BaseViewModel
import com.example.a5month_youtube.data.remote.model.Item
import com.example.a5month_youtube.data.remote.model.PlaylistsItem
import com.example.a5month_youtube.repository.Repository
import com.example.a5month_youtube.result.Resource

class DetailViewModel(private val repository: Repository): BaseViewModel() {
    private val mutableDetailId: MutableLiveData<List<String>> = MutableLiveData()
    val liveDetailId: LiveData<List<String>> = mutableDetailId

    fun getDetail(playListId: String, itemCount: Int): LiveData<Resource<PlaylistsItem>>{
        return repository.getDetail(playListId, itemCount)
    }

    fun getPlaylistItem(playlistId: String?): LiveData<Resource<PlaylistsItem>> {
        return repository.getDetail(playlistId!!, 100)
    }

    fun getDetailId(data: List<Item>){
        val result = arrayListOf<String>()
        for (i in data) {
            result.add(i.contentDetails.videoId)
        }
        mutableDetailId.value = result
    }
}