package com.shpzdsh.musicapp.presentation.track

import androidx.lifecycle.viewModelScope
import com.shpzdsh.musicapp.core.BaseMusicViewModel
import com.shpzdsh.musicapp.data.api.models.TrackDetailsResponse
import com.shpzdsh.musicapp.domain.models.TrackDetail
import com.shpzdsh.musicapp.domain.track.LoadTrackUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrackViewModel(private val loadTrackUseCase: LoadTrackUseCase) : BaseMusicViewModel() {

    private val _trackFlow : MutableStateFlow<TrackDetail?> = MutableStateFlow(null)
    val trackFlow = _trackFlow.asStateFlow()

    fun loadChart(id: Long) {
        viewModelScope.launch {
            startLoad()
            runCatching { loadTrackUseCase(id) }
                .onEndLoad()
                .onSuccess { _trackFlow.value = it }
                .onFailure { processError(it) }
        }
    }

    fun loadNext() {
        viewModelScope.launch {
            startLoad()
            runCatching { loadTrackUseCase.nextTrack() }
                .onEndLoad()
                .onSuccess { _trackFlow.value = it }
                .onFailure { processError(it) }
        }
    }

    fun loadPrev() {
        viewModelScope.launch {
            startLoad()
            runCatching { loadTrackUseCase.prevTrack() }
                .onEndLoad()
                .onSuccess { _trackFlow.value = it }
                .onFailure { processError(it) }
        }
    }
}