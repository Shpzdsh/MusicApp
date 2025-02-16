package com.shpzdsh.musicapp.data

import com.shpzdsh.musicapp.data.api.MusicApi
import com.shpzdsh.musicapp.data.api.models.toChartInfo
import com.shpzdsh.musicapp.data.api.models.toTrackDetail
import com.shpzdsh.musicapp.domain.MusicRepository
import com.shpzdsh.musicapp.domain.models.ChartInfo
import com.shpzdsh.musicapp.domain.models.CurrentPlayingTrackInfo
import com.shpzdsh.musicapp.domain.models.TrackDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MusicRepositoryImpl(private val musicApi: MusicApi): MusicRepository {

    private val _currentTrackFlow: MutableStateFlow<CurrentPlayingTrackInfo?> = MutableStateFlow(null)
    override val currentTrackFlow: Flow<CurrentPlayingTrackInfo?> = _currentTrackFlow.asStateFlow()

    private var currentTrackList: List<ChartInfo> = emptyList()

    override suspend fun setupCurrentTrack(currentTrack: CurrentPlayingTrackInfo) {
        _currentTrackFlow.value = currentTrack
    }

    override suspend fun nextTrack(): TrackDetail? {
        val current = _currentTrackFlow.value ?: return null
        val item = if (current.index >= currentTrackList.lastIndex) {
            currentTrackList[0]
        } else {
            currentTrackList[current.index + 1]
        }
        return track(item.id)
    }

    override suspend fun prevTrack(): TrackDetail? {
        val current = _currentTrackFlow.value ?: return null
        val item =  if (current.index <= 0) {
            currentTrackList[currentTrackList.lastIndex]
        } else {
            currentTrackList[current.index - 1]
        }
        return track(item.id)
    }

    override suspend fun loadChart(): List<ChartInfo> =
        musicApi.chart().tracks.content
            .map { it.toChartInfo() }
            .also {
            currentTrackList = it
        }

    override suspend fun search(query: String): List<ChartInfo> =
        musicApi.search(query).content
            .map { it.toChartInfo() }
            .also {
            currentTrackList = it
        }

    override suspend fun track(id: Long): TrackDetail =
        musicApi.getTrack(id).toTrackDetail().also { item ->
            val currentInd = currentTrackList.indexOfFirst { item.id == id }
            _currentTrackFlow.value = CurrentPlayingTrackInfo(data = item, index = currentInd, time = 0L, isPlaying = false)
        }
}