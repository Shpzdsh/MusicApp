package com.shpzdsh.musicapp.data.api.models

import com.google.gson.annotations.SerializedName
import com.shpzdsh.musicapp.data.api.models.Album
import com.shpzdsh.musicapp.data.api.models.Artist
import com.shpzdsh.musicapp.domain.models.ChartInfo

data class Data(
    @SerializedName("album")
    val album: Album,
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,
    @SerializedName("id")
    val id: Long,
    @SerializedName("link")
    val link: String,
    @SerializedName("md5_image")
    val md5Image: String,
    @SerializedName("preview")
    val preview: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("readable")
    val readable: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_short")
    val titleShort: String,
    @SerializedName("title_version")
    val titleVersion: String,
    @SerializedName("type")
    val type: String
)

internal fun Data.toChartInfo() = ChartInfo(
    cover = album.coverSmall, name = title, author = artist.name, duration = duration, id = id
)