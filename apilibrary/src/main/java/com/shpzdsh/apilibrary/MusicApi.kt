package com.shpzdsh.apilibrary

import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query


interface MusicApi {

    @GET("/search")
    suspend fun search(@Query("q") query: String)
}

fun musicApi() : MusicApi {
    val retrofit = Retrofit.Builder().baseUrl("https://api.deezer.com").build()
    return retrofit.create(MusicApi::class.java )
}