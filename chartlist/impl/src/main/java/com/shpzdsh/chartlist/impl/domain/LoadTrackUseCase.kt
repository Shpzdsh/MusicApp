package com.shpzdsh.chartlist.impl.domain

import com.shpzdsh.apilibrary.models.TrackDetailsResponse

class LoadTrackUseCase(private val musicRepository: MusicRepository){
    suspend operator fun invoke(id: Long): TrackDetailsResponse =
        musicRepository.track(id)
}