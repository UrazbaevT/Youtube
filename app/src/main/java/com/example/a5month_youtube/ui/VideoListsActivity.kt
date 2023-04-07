package com.example.a5month_youtube.ui

import android.widget.Toast
import com.example.a5month_youtube.base.BaseActivity
import com.example.a5month_youtube.base.BaseViewModel
import com.example.a5month_youtube.databinding.ActivityVideoListBinding

class VideoListsActivity(override val viewModel: BaseViewModel) : BaseActivity<ActivityVideoListBinding, BaseViewModel>() {
    override fun inflateViewBinding(): ActivityVideoListBinding {
        return ActivityVideoListBinding.inflate(layoutInflater)
    }

    override fun initListener() {
        super.initListener()
        val result = intent.getStringExtra(PlaylistsActivity.ID)
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }
}