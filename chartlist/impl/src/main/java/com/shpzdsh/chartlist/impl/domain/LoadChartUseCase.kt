package com.shpzdsh.chartlist.impl.domain

import com.shpzdsh.apilibrary.models.Data

class LoadChartUseCase(private val musicRepository: MusicRepository){
    suspend operator fun invoke(): List<Data> =
        musicRepository.loadChart()
}