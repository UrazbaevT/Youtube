package com.example.a5month_youtube.data.remote

import com.example.a5month_youtube.BuildConfig
import com.example.a5month_youtube.core.network.BaseDataSource
import com.example.a5month_youtube.data.remote.model.PlayLists
import com.example.a5month_youtube.data.remote.model.PlaylistItem
import com.example.a5month_youtube.result.Resource
import com.example.a5month_youtube.utils.Const
import org.koin.dsl.module

val remoteDataSource = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {


    suspend fun getPlayLists(): Resource<PlayLists> {
        return getResult {
            apiService.getPlayLists(
                BuildConfig.API_KEY, Const.part, Const.channelId
            )
        }
    }

    suspend fun getDetail(playlistId: String): Resource<PlaylistItem> {
        return getResult {
            apiService.playlistItems(
                BuildConfig.API_KEY, Const.part, playlistId, 20
            )
        }
    }

    suspend fun getVideo(id: String?) = getResult {
        apiService.getVideo(
            BuildConfig.API_KEY,
            Const.part,
            id!!
        )
    }

}