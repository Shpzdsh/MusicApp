package com.shpzdsh.apilibrary.models

import com.google.gson.annotations.SerializedName

data class Data(

    @SerializedName("album")
    val album: Album,
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("explicit_content_cover")
    val explicit_content_cover: Int,
    @SerializedName("explicit_content_lyrics")
    val explicit_content_lyrics: Int,
    @SerializedName("explicit_lyrics")
    val explicit_lyrics: Boolean,
    @SerializedName("id")
    val id: Long,
    @SerializedName("link")
    val link: String,
    @SerializedName("md5_image")
    val md5_image: String,
    @SerializedName("preview")
    val preview: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("readable")
    val readable: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_short")
    val title_short: String,
    @SerializedName("title_version")
    val title_version: String,
    @SerializedName("type")
    val type: String
)