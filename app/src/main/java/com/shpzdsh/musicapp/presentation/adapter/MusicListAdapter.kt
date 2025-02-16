package com.shpzdsh.musicapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.shpzdsh.musicapp.core.formatDuration
import com.shpzdsh.musicapp.databinding.MusicItemBinding
import com.shpzdsh.musicapp.domain.models.ChartInfo

class MusicListAdapter (private val onMusicClick: (Long) -> Unit) :
    ListAdapter<ChartInfo, MusicListAdapter.ViewHolder>(MusicItemDiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            MusicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.bind(item)
        }

        inner class ViewHolder(private val binding: MusicItemBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(item: ChartInfo) = with(binding) {
                trackCover.load(item.cover)
                trackTitle.text = item.name
                trackArtist.text = item.author
                trackDuration.text = formatDuration(item.duration)
                binding.root.setOnClickListener{
                    onMusicClick(item.id)
                }
            }
        }
}