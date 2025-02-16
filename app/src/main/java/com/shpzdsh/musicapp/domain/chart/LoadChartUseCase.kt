package com.shpzdsh.musicapp.domain.chart

import com.shpzdsh.musicapp.domain.MusicRepository
import com.shpzdsh.musicapp.domain.models.ChartInfo

class LoadChartUseCase(private val musicRepository: MusicRepository){
    suspend operator fun invoke(): List<ChartInfo> =
        musicRepository.loadChart()
}