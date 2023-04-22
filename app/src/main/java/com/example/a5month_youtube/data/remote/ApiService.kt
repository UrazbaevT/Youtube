package com.example.a5month_youtube.data.remote

import com.example.a5month_youtube.data.remote.model.PlayLists
import com.example.a5month_youtube.data.remote.model.PlaylistItem
import com.example.a5month_youtube.data.remote.model.Videos
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

    @GET("playlistItems")
    suspend fun playlistItems(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("playlistId") channelId: String,
        @Query("maxResults") maxResults : Int
    ): Response<PlaylistItem>

    @GET("videos")
    suspend fun getVideo(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("id") id: String
    ): Response<Videos>

}