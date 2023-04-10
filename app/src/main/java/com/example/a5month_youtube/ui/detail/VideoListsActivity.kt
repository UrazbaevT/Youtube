package com.example.a5month_youtube.ui.detail

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.a5month_youtube.core.ui.BaseActivity
import com.example.a5month_youtube.databinding.ActivityVideoListBinding
import com.example.a5month_youtube.ui.playlists.PlaylistsViewModel
import com.example.a5month_youtube.ui.playlists.PlaylistsActivity
import com.example.a5month_youtube.core.ui.BaseViewModel

class VideoListsActivity : BaseActivity<ActivityVideoListBinding, BaseViewModel>() {
    override fun inflateViewBinding(): ActivityVideoListBinding {
        return ActivityVideoListBinding.inflate(layoutInflater)
    }

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun initListener() {
        super.initListener()
        val result = intent.getStringExtra(PlaylistsActivity.ID)
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }
}