package com.shpzdsh.chartlist.impl.presentation.track

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shpzdsh.apilibrary.models.TrackDetailsResponse
import com.shpzdsh.chartlist.impl.domain.LoadTrackUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrackViewModel(private val loadTrackUseCase: LoadTrackUseCase) : ViewModel() {
    private val _trackFlow : MutableStateFlow<TrackDetailsResponse?> = MutableStateFlow(null)
    val trackFlow = _trackFlow.asStateFlow()



    fun loadChart(id: Long) {
        viewModelScope.launch {
            val cont = loadTrackUseCase(id)
            _trackFlow.value = cont
        }
    }
}
class TrackViewModelFactory(private val loadTrackUseCase: LoadTrackUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrackViewModel::class.java)) {
            return TrackViewModel(loadTrackUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}