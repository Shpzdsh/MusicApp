package com.shpzdsh.musicapp.di

import com.shpzdsh.musicapp.data.api.MusicApi
import retrofit2.Retrofit
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Retrofit> { provideRetrofit() }
    single<MusicApi> { provideGameApi(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideGameApi(retrofit: Retrofit): MusicApi =
    retrofit.create(MusicApi::class.java)

private const val BASE_URL = "https://api.deezer.com"