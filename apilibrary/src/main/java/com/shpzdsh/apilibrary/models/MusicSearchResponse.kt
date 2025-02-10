package com.shpzdsh.apilibrary.models

import com.google.gson.annotations.SerializedName


data class MusicSearchResponse(

    @SerializedName("data")
    val content: List<Data>,

    @SerializedName("total")
    val totalCount: Int
)