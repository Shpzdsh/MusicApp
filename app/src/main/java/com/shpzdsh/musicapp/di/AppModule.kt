package com.shpzdsh.musicapp.di

import com.shpzdsh.musicapp.data.MusicRepositoryImpl
import com.shpzdsh.musicapp.domain.chart.LoadChartUseCase
import com.shpzdsh.musicapp.domain.track.LoadTrackUseCase
import com.shpzdsh.musicapp.domain.MusicRepository
import com.shpzdsh.musicapp.domain.search.LoadSearchUseCase
import com.shpzdsh.musicapp.presentation.chartlist.ChartListViewModel
import com.shpzdsh.musicapp.presentation.track.TrackViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<MusicRepository> { MusicRepositoryImpl(get()) }

    single<LoadChartUseCase> { LoadChartUseCase(get()) }
    single<LoadTrackUseCase> { LoadTrackUseCase(get()) }
    single<LoadSearchUseCase> { LoadSearchUseCase(get()) }

    viewModel<TrackViewModel> { TrackViewModel(get()) }
    viewModel<ChartListViewModel> { ChartListViewModel(get(), get()) }
}