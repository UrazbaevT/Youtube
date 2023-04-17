package com.example.a5month_youtube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.a5month_youtube.data.remote.RemoteDataSource
import com.example.a5month_youtube.data.remote.model.PlayLists
import com.example.a5month_youtube.data.remote.model.PlaylistsItem
import com.example.a5month_youtube.result.Resource
import kotlinx.coroutines.Dispatchers

class Repository {

    private val dataSource:RemoteDataSource by lazy {
        RemoteDataSource()
    }

    fun getPlayLists(): LiveData<Resource<PlayLists>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlayLists()
            emit(response)
        }
    }

    fun getDetail(playlistId: String, itemCount: Int): LiveData<Resource<PlaylistsItem>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getDetail(playlistId, itemCount)
            emit(response)
        }
    }

}