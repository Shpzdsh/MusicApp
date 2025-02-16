package com.shpzdsh.musicapp.data.api

import com.shpzdsh.musicapp.data.api.models.ChartResponse
import com.shpzdsh.musicapp.data.api.models.MusicSearchResponse
import com.shpzdsh.musicapp.data.api.models.TrackDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MusicApi {

    @GET("/chart")
    suspend fun chart() : ChartResponse

    @GET("/search")
    suspend fun search(@Query("q") query: String) : MusicSearchResponse

    @GET("/track/{id}")
    suspend fun getTrack(@Path("id") id: Long) : TrackDetailsResponse
}