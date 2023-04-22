package com.example.a5month_youtube.ui.detail

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a5month_youtube.core.ext.ConnectionLiveData
import com.example.a5month_youtube.core.ui.BaseActivity
import com.example.a5month_youtube.data.remote.model.PlaylistItem
import com.example.a5month_youtube.databinding.ActivityDetailBinding
import com.example.a5month_youtube.ui.detail.adapter.DetailAdapter
import com.example.a5month_youtube.ui.player.PlayerActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity() : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: DetailAdapter

    override val viewModel: DetailViewModel by viewModel()

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
        adapter = DetailAdapter(this::onClick)
        binding.videosRv.layoutManager = LinearLayoutManager(this)
        binding.videosRv.adapter = adapter
    }

    override fun initViewModel() {
        super.initViewModel()
        val getId = intent.getStringExtra("id")
        val getTitle = intent.getStringExtra("title")
        val getDesc = intent.getStringExtra("desc")
        val getCount = intent.getIntExtra("count", 0)

        viewModel.getPlaylistItem(getId).observe(this) {
            it.data?.let { it1 -> adapter.addList(it1.items) }
            binding.tvTitle.text = getTitle
            binding.tvDesc.text = getDesc
        }
    }

    override fun initListener() {
        super.initListener()
        binding.backTv.setOnClickListener { finish() }
    }

    private fun onClick(item: PlaylistItem.Item) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra("id1", item.contentDetails?.videoId)
        intent.putExtra("title1", item.snippet?.title)
        intent.putExtra("desc1", item.snippet?.description)
        startActivity(intent)
    }

}