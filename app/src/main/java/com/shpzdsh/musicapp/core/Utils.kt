package com.shpzdsh.musicapp.core

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun formatDuration(durationInSeconds: Int): String {
    val minutes = durationInSeconds / 60
    val seconds = durationInSeconds % 60
    return String.format("%d:%02d", minutes, seconds)
}