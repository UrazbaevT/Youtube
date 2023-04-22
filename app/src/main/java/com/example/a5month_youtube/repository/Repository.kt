package com.example.a5month_youtube.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.a5month_youtube.data.remote.RemoteDataSource
import com.example.a5month_youtube.data.remote.model.PlayLists
import com.example.a5month_youtube.data.remote.model.PlaylistItem
import com.example.a5month_youtube.data.remote.model.Videos
import com.example.a5month_youtube.result.Resource
import kotlinx.coroutines.Dispatchers
import kotlin.collections.forEach as forEach

class Repository(private val dataSource: RemoteDataSource) {

    fun getPlaylists(): LiveData<Resource<PlayLists>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlayLists()
            emit(response)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun getPlaylistItem(playlistId: String): LiveData<Resource<PlaylistItem>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getDetail(playlistId)

            val list = ArrayList<String>()

            response.data?.items?.forEach { item ->
                val items = dataSource.getVideo(item?.contentDetails?.videoId)
                items.data?.items?.get(0)?.contentDetails?.let { list.add(it.duration)
                }

                list.forEachIndexed { index, s ->
                    response.data.items[index]?.date = s
                }
            }
            emit(response)
        }
    }

    fun getVideo(id: String): LiveData<Resource<Videos>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getVideo(id)
            emit(response)
        }
    }
}