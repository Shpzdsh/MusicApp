package com.shpzdsh.musicapp.domain.models

data class CurrentPlayingTrackInfo(
    val data: TrackDetail,
    val index: Int,
    val isPlaying: Boolean,
    val time: Long
)
