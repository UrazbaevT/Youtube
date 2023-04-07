package com.example.a5month_youtube.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a5month_youtube.BuildConfig
import com.example.a5month_youtube.base.BaseViewModel
import com.example.a5month_youtube.model.PlayLists
import com.example.a5month_youtube.remote.ApiService
import com.example.a5month_youtube.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class MainViewModel: BaseViewModel() {

    private val apiService: ApiService = RetrofitClient.create()

    fun playlists(): LiveData<PlayLists>{
        return getPlayLists()
    }

    private fun getPlayLists(): LiveData<PlayLists> {
        val data = MutableLiveData<PlayLists>()
        apiService.getPlayLists(BuildConfig.API_KEY, CONTENT_DETAILS_SNIPPET, CHANELL_ID)
            .enqueue(object: retrofit2.Callback<PlayLists>{
                override fun onResponse(call: Call<PlayLists>, response: Response<PlayLists>) {
                    if (response.isSuccessful){
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<PlayLists>, t: Throwable) {
                    print(t.stackTrace)
                    //404 - not found, 401 - token invalid, 403 - access denied
                 }
            })
        return data
    }

    companion object{
        const val CONTENT_DETAILS_SNIPPET = "contentDetails,snippet"
        const val CHANELL_ID = "UCWOA1ZGywLbqmigxE4Qlvuw"
    }
}