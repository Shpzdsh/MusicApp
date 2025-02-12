package com.shpzdsh.chartlist.impl.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.shpzdsh.apilibrary.models.Data

class MusicItemDiffCallback: DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}