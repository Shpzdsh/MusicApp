package com.shpzdsh.musicapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.shpzdsh.musicapp.data.api.models.Data
import com.shpzdsh.musicapp.domain.models.ChartInfo

class MusicItemDiffCallback: DiffUtil.ItemCallback<ChartInfo>() {
    override fun areItemsTheSame(oldItem: ChartInfo, newItem: ChartInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChartInfo, newItem: ChartInfo): Boolean {
        return oldItem == newItem
    }
}