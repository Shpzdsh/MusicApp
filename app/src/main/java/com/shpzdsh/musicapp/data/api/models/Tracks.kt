package com.shpzdsh.musicapp.data.api.models

import com.google.gson.annotations.SerializedName
import com.shpzdsh.musicapp.data.api.models.Data

data class Tracks(

    @SerializedName("data")
    val content: List<Data>,

    @SerializedName("total")
    val totalCount: Int
)