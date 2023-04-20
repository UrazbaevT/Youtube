package com.example.a5month_youtube.ui.detail

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a5month_youtube.core.ext.ConnectionLiveData
import com.example.a5month_youtube.core.ui.BaseActivity
import com.example.a5month_youtube.databinding.ActivityDetailBinding
import com.example.a5month_youtube.ui.detail.adapter.DetailAdapter
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
        adapter = DetailAdapter()
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