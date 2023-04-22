package com.example.a5month_youtube.ui.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a5month_youtube.core.ext.loadImage
import com.example.a5month_youtube.data.remote.model.Item
import com.example.a5month_youtube.data.remote.model.PlaylistItem
import com.example.a5month_youtube.databinding.ItemDetailBinding

class DetailAdapter(private val onClick: (PlaylistItem.Item) -> Unit) :
    RecyclerView.Adapter<DetailAdapter.PlaylistItemViewHolder>() {
    private var playlistItem = listOf<PlaylistItem.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<PlaylistItem.Item?>?) {
        this.playlistItem = list as List<PlaylistItem.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemViewHolder {
        return PlaylistItemViewHolder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistItemViewHolder, position: Int) {
        val playlist = playlistItem[position]
        holder.bind(playlist)
    }

    override fun getItemCount(): Int {
        return playlistItem.size
    }

    inner class PlaylistItemViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item:PlaylistItem.Item) {
            with(binding) {
                item.snippet?.thumbnails?.standard?.url?.let { image.loadImage(it) }
                tvTitle.text = item.snippet?.title
                tvTimeOfVideo.text  = item.date?.let { ConvertDuration.convertDuration(it) }
                binding.image.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }
}

object ConvertDuration{
    fun convertDuration(duration: String): String {
        val regex = "^PT(\\d+)M(\\d+)S$".toRegex()
        val matchResult = regex.find(duration)
        if (matchResult != null && matchResult.groupValues.size == 3) {
            val minutes = matchResult.groupValues[1].toInt()
            val seconds = matchResult.groupValues[2].toInt()
            return "$minutes:${String.format("%02d", seconds)}"
        }
        return ""
    }
}
