package com.shpzdsh.chartlist.impl.presentation.adapter

import androidx.recyclerview.widget.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shpzdsh.api.databinding.MusicItemBinding
import com.shpzdsh.apilibrary.models.Data

class MusicListAdapter (private val onMusicClick: (Int) -> Unit) :
    ListAdapter<Data, MusicListAdapter.ViewHolder>(MusicItemDiffCallback()) {

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

            fun bind(item: Data) = with(binding) {
                musicTitle.text = item.title

            }
        }
}