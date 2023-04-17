package com.example.a5month_youtube.ui.detail

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a5month_youtube.core.ext.ConnectionLiveData
import com.example.a5month_youtube.core.ui.BaseActivity
import com.example.a5month_youtube.ui.playlists.PlaylistsActivity
import com.example.a5month_youtube.data.remote.model.Item
import com.example.a5month_youtube.data.remote.model.PlaylistInfo
import com.example.a5month_youtube.databinding.ActivityDetailBinding
import com.example.a5month_youtube.result.Status
import com.example.a5month_youtube.ui.detail.adapter.DetailAdapter

class DetailActivity() : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: DetailAdapter

    override lateinit var viewModel: DetailViewModel

    override fun checkConnection() {
        super.checkConnection()
        ConnectionLiveData(application).observe(this) { isConnected ->
            if (isConnected) {
                binding.noInternetConnection.visibility = View.GONE
                binding.internetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.VISIBLE
                binding.internetConnection.visibility = View.GONE
            }
        }
    }

    override fun initView() {
        super.initView()
        adapter = DetailAdapter()
        binding.videosRv.layoutManager = LinearLayoutManager(this)
        binding.videosRv.adapter = adapter
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        val getId = intent.getStringExtra("id")
        val getTitle = intent.getStringExtra("title")
        val getDesc = intent.getStringExtra("desc")
        val getCount = intent.getIntExtra("count" ,0)

        viewModel.getPlaylistItem(getId).observe(this) {
            it.data?.let { it1 -> adapter.addData(it1.items) }
            binding.tvTitle.text = getTitle
            binding.tvDesc.text = getDesc
//            binding.tvCounterVideo.text = "$getCount video series"
        }
    }

    override fun initListener() {
        super.initListener()
        binding.backTv.setOnClickListener { finish() }
    }

    companion object {
        const val DETAIL_KEY = "detail_key"
    }
}