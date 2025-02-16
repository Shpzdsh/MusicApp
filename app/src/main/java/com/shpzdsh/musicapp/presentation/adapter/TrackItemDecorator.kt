package com.shpzdsh.musicapp.presentation.adapter

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class TrackItemDecorator(@DimenRes private val spaceDimen: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = parent.context.resources.getDimension(spaceDimen).toInt()
    }
}