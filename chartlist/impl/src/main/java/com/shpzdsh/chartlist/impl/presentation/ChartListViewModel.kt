package com.shpzdsh.chartlist.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shpzdsh.apilibrary.models.Data
import com.shpzdsh.chartlist.impl.domain.LoadChartUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChartListViewModel(private val loadChartUseCase: LoadChartUseCase) : ViewModel() {

    private val _trackFlow : MutableStateFlow<List<Data>> = MutableStateFlow(listOf())
    val trackFlow = _trackFlow.asStateFlow()



    fun loadChart() {
        viewModelScope.launch {
            val cont = loadChartUseCase()
            _trackFlow.value = cont
        }
    }
}
class ChartListViewModelFactory(private val loadChartUseCase: LoadChartUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChartListViewModel::class.java)) {
            return ChartListViewModel(loadChartUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
