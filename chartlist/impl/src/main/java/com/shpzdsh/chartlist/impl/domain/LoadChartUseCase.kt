package com.shpzdsh.chartlist.impl.domain

import com.shpzdsh.apilibrary.models.Data
import com.shpzdsh.apilibrary.musicApi

class LoadChartUseCase(private val musicRepository: MusicRepository){
    suspend operator fun invoke(): List<Data> =
        musicRepository.loadChart()
}