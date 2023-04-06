package com.example.a5month_youtube.ui

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.a5month_youtube.base.BaseActivity
import com.example.a5month_youtube.base.BaseViewModel
import com.example.a5month_youtube.databinding.ActivityMainBinding

class MainActivity() : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel.playlists().observe(this) {
            Toast.makeText(this, it.kind.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}