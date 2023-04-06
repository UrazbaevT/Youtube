package com.example.a5month_youtube.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a5month_youtube.BuildConfig
import com.example.a5month_youtube.base.BaseViewModel
import com.example.a5month_youtube.model.Playlist
import com.example.a5month_youtube.remote.ApiService
import com.example.a5month_youtube.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class MainViewModel: BaseViewModel() {

    private val apiService: ApiService = RetrofitClient.create()

    fun playlists(): LiveData<Playlist>{
        return getPlayLists()
    }

    private fun getPlayLists(): LiveData<Playlist> {
        val data = MutableLiveData<Playlist>()
        apiService.getPlayLists(BuildConfig.API_KEY,"contentDetails","UCWOA1ZGywLbqmigxE4Qlvuw")
            .enqueue(object: retrofit2.Callback<Playlist>{
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful){
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    print(t.stackTrace)
                    //404 - not found, 401 - token invalid, 403 - access denied
                 }
            })
        return data
    }
}