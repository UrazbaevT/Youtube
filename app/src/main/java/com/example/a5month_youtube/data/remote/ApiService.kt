package com.example.a5month_youtube.data.remote

import com.example.a5month_youtube.data.remote.model.PlayLists
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlayLists(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int = 20
    ): Response<PlayLists>

}