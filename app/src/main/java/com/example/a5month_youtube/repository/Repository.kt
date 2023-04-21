package com.example.a5month_youtube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.a5month_youtube.data.remote.RemoteDataSource
import com.example.a5month_youtube.data.remote.model.PlayLists
import com.example.a5month_youtube.data.remote.model.PlaylistsItem
import com.example.a5month_youtube.data.remote.model.Videos
import com.example.a5month_youtube.result.Resource
import kotlinx.coroutines.Dispatchers

class Repository(private val dataSource: RemoteDataSource) {

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

            val list = ArrayList<String>()

            response.data?.items?.forEach { item ->
                val items = dataSource.getVideo(item.contentDetails.videoId)
                items.data?.items?.get(0)?.contentDetails?.let { list.add(it.duration)
                }

                list.forEachIndexed { index, s ->
                    response.data.items[index].date = s
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