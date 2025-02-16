package com.shpzdsh.musicapp.domain.track

import com.shpzdsh.musicapp.domain.MusicRepository
import com.shpzdsh.musicapp.domain.models.TrackDetail

class LoadTrackUseCase(private val musicRepository: MusicRepository) {
    suspend operator fun invoke(id: Long): TrackDetail =
        musicRepository.track(id)

    suspend fun nextTrack(): TrackDetail? =
        musicRepository.nextTrack()

    suspend fun prevTrack(): TrackDetail? =
        musicRepository.prevTrack()
}