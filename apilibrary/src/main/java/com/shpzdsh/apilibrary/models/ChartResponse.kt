package com.shpzdsh.apilibrary.models

import com.google.gson.annotations.SerializedName

data class ChartResponse(
    @SerializedName("tracks")
    val tracks: Tracks
)