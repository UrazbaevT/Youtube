package com.example.a5month_youtube.ui

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.a5month_youtube.base.BaseActivity
import com.example.a5month_youtube.base.BaseViewModel
import com.example.a5month_youtube.databinding.ActivityPlaylistsBinding
import com.example.a5month_youtube.model.Item

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, BaseViewModel>() {

    private  var adapter= PlaylistAdapter(this::onClick)

    private fun onClick(item: Item) {
        val intent = Intent(this@PlaylistsActivity, VideoListsActivity::class.java)
        intent.putExtra(ID, item.snippet.title)
        Toast.makeText(this, item.snippet.title, Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun initViewModel() {

        viewModel.playlists().observe(this) {
            binding.recyclerView.adapter = adapter

            adapter.setList(it.items)
            Toast.makeText(this, it.kind, Toast.LENGTH_SHORT).show()
        }
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    companion object {
        const val ID = "id"
    }
}