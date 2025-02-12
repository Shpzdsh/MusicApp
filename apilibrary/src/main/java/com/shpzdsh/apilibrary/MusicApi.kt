package com.shpzdsh.apilibrary

import com.shpzdsh.apilibrary.models.MusicSearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query


interface MusicApi {

    @GET("/search")
    suspend fun search(@Query("q") query: String) : MusicSearchResponse
}

fun musicApi() : MusicApi {
    val retrofit = Retrofit.Builder().baseUrl("https://api.deezer.com").addConverterFactory(GsonConverterFactory.create()).build()
    return retrofit.create(MusicApi::class.java )
}