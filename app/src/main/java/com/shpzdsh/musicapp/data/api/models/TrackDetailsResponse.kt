package com.shpzdsh.musicapp.data.api.models

import com.google.gson.annotations.SerializedName
import com.shpzdsh.musicapp.data.api.models.Album
import com.shpzdsh.musicapp.data.api.models.Artist
import com.shpzdsh.musicapp.domain.models.TrackDetail

data class TrackDetailsResponse(
    @SerializedName("album")
    val album: Album,
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("available_countries")
    val availableCountries: List<String>,
    @SerializedName("bpm")
    val bpm: Double,
    @SerializedName("disk_number")
    val diskNumber: Int,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,
    @SerializedName("gain")
    val gain: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isrc")
    val isrc: String,
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
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("share")
    val share: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_short")
    val titleShort: String,
    @SerializedName("title_version")
    val titleVersion: String,
    @SerializedName("track_position")
    val trackPosition: Int,
    @SerializedName("track_token")
    val trackToken: String,
    @SerializedName("type")
    val type: String
)

internal fun TrackDetailsResponse.toTrackDetail() = TrackDetail(
    cover = album.coverBig,
    title = title,
    author = artist.name,
    duration = duration,
    trackPreview = preview,
    id = id.toLong()

)