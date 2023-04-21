package com.example.a5month_youtube.ui.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a5month_youtube.core.ext.loadImage
import com.example.a5month_youtube.data.remote.model.Item
import com.example.a5month_youtube.data.remote.model.Items
import com.example.a5month_youtube.databinding.ItemDetailBinding

class DetailAdapter(private val onClick: (Item) -> Unit) :
    RecyclerView.Adapter<DetailAdapter.PlaylistItemViewHolder>() {
    private val data = arrayListOf<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(newData: List<Item>) {
        data.clear()
        data.addAll(newData)
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
        return holder.bind(data[position], null)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class PlaylistItemViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, videosItem: Items?) {
            with(binding) {
                image.loadImage(item.snippet.thumbnails.standard.url)
                tvTitle.text = item.snippet.title
                tvTimeOfVideo.text =
                    videosItem?.contentDetails?.let { convertDuration(it.duration) }
                binding.image.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }

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
