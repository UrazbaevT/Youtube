package com.example.a5month_youtube.data.remote

import com.example.a5month_youtube.BuildConfig
import com.example.a5month_youtube.core.network.BaseDataSource
import com.example.a5month_youtube.core.network.RetrofitClient
import com.example.a5month_youtube.data.remote.model.PlayLists
import com.example.a5month_youtube.data.remote.model.PlaylistsItem
import com.example.a5month_youtube.result.Resource
import com.example.a5month_youtube.utils.Const

class RemoteDataSource : BaseDataSource() {
    private val apiService: ApiService = RetrofitClient.create()

    suspend fun getPlayLists():Resource<PlayLists> {
        return getResult {
            apiService.getPlayLists(
                BuildConfig.API_KEY,
                Const.part,
                Const.channelId
            )
        }
    }

    suspend fun getDetail(playlistId: String, itemCount: Int):Resource<PlaylistsItem> {
        return getResult {
           apiService.playlistItems(
               BuildConfig.API_KEY,
               Const.part,
               playlistId,
               itemCount
           )
        }
    }

}