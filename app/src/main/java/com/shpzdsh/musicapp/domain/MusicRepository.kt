package com.shpzdsh.musicapp.domain

import com.shpzdsh.musicapp.domain.models.ChartInfo
import com.shpzdsh.musicapp.domain.models.CurrentPlayingTrackInfo
import com.shpzdsh.musicapp.domain.models.TrackDetail
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    val currentTrackFlow: Flow<CurrentPlayingTrackInfo?>
    suspend fun setupCurrentTrack(currentTrack: CurrentPlayingTrackInfo)
    suspend fun nextTrack(): TrackDetail?
    suspend fun prevTrack(): TrackDetail?
    suspend fun loadChart(): List<ChartInfo>
    suspend fun search(query: String): List<ChartInfo>
    suspend fun track(id: Long): TrackDetail
}