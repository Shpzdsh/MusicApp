package com.shpzdsh.musicapp

import android.app.Application
import com.shpzdsh.musicapp.di.appModule
import com.shpzdsh.musicapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MusicApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MusicApp)
            modules(listOf(networkModule, appModule))
        }
    }
}