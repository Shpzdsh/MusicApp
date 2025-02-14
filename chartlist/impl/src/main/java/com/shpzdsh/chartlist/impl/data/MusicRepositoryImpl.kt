package com.shpzdsh.chartlist.impl.data

import com.shpzdsh.apilibrary.MusicApi
import com.shpzdsh.apilibrary.models.Data
import com.shpzdsh.chartlist.impl.domain.MusicRepository

class MusicRepositoryImpl(private val musicApi: MusicApi): MusicRepository {

    override suspend fun loadChart(): List<Data> =
        musicApi.chart().tracks.content



}