package com.shpzdsh.chartlist.impl

import android.app.Application
import com.shpzdsh.apilibrary.musicApi
import com.shpzdsh.chartlist.impl.data.MusicRepositoryImpl
import com.shpzdsh.chartlist.impl.domain.LoadChartUseCase
import com.shpzdsh.chartlist.impl.domain.LoadTrackUseCase

class MusicApp : Application() {
    val retrofitApi = musicApi()
    val musicRepository = MusicRepositoryImpl(musicApi())
    val loadChartUseCase: LoadChartUseCase = LoadChartUseCase(musicRepository)
    val loadTrackUseCase: LoadTrackUseCase = LoadTrackUseCase(musicRepository)
}