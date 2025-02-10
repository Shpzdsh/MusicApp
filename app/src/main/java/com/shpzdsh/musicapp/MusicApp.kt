package com.shpzdsh.musicapp

import android.app.Application
import com.shpzdsh.apilibrary.musicApi

class MusicApp : Application() {
    val retrofitApi = musicApi()
}