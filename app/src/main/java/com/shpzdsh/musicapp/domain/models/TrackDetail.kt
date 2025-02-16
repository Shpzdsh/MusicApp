package com.shpzdsh.musicapp.domain.models

data class TrackDetail(
    val cover: String,
    val title: String,
    val author: String,
    val duration: Int,
    val trackPreview: String,
    val id: Long
)
