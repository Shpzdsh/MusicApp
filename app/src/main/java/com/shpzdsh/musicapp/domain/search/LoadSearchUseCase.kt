package com.shpzdsh.musicapp.domain.search

import com.shpzdsh.musicapp.domain.MusicRepository
import com.shpzdsh.musicapp.domain.models.ChartInfo

class LoadSearchUseCase(private val musicRepository: MusicRepository){
    suspend operator fun invoke(query: String): List<ChartInfo> =
        musicRepository.search(query)
}