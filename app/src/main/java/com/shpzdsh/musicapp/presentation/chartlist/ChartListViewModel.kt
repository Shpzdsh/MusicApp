package com.shpzdsh.musicapp.presentation.chartlist

import androidx.lifecycle.viewModelScope
import com.shpzdsh.musicapp.core.BaseMusicViewModel
import com.shpzdsh.musicapp.data.api.models.Data
import com.shpzdsh.musicapp.domain.chart.LoadChartUseCase
import com.shpzdsh.musicapp.domain.models.ChartInfo
import com.shpzdsh.musicapp.domain.search.LoadSearchUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.http.Query

class ChartListViewModel(
    private val loadChartUseCase: LoadChartUseCase,
    private val loadSearchUseCase: LoadSearchUseCase
) : BaseMusicViewModel() {

    private val _trackFlow : MutableStateFlow<List<ChartInfo>> = MutableStateFlow(listOf())
    val trackFlow = _trackFlow.asStateFlow()

    private var searchJob: Job? = null // Для управления корутиной поиска

    fun loadChart() {
        viewModelScope.launch {
            startLoad()
            runCatching { loadChartUseCase() }
                .onEndLoad()
                .onSuccess { _trackFlow.value = it }
                .onFailure { processError(it) }
        }
    }

    fun search(query: String) {
        searchJob?.cancel()
        searchJob = CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            startLoad()
            runCatching { if (query.isEmpty()) loadChartUseCase() else loadSearchUseCase(query) }
                .onEndLoad()
                .onSuccess { _trackFlow.value = it }
                .onFailure { processError(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
}
