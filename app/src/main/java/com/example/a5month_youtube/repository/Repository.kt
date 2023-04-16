package com.example.a5month_youtube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.a5month_youtube.BuildConfig
import com.example.a5month_youtube.core.network.RetrofitClient
import com.example.a5month_youtube.data.remote.ApiService
import com.example.a5month_youtube.data.remote.RemoteDataSource
import com.example.a5month_youtube.data.remote.model.PlayLists
import com.example.a5month_youtube.result.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Response

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
}