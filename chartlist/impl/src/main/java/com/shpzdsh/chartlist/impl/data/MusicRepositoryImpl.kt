package com.shpzdsh.chartlist.impl.data

import com.shpzdsh.apilibrary.MusicApi
import com.shpzdsh.apilibrary.models.Data
import com.shpzdsh.apilibrary.models.TrackDetailsResponse
import com.shpzdsh.chartlist.impl.domain.MusicRepository

class MusicRepositoryImpl(private val musicApi: MusicApi): MusicRepository {

    override suspend fun loadChart(): List<Data> =
        musicApi.chart().tracks.content

    override suspend fun search(query: String): List<Data> =
        musicApi.search(query).content

    override suspend fun track(id: Long): TrackDetailsResponse =
        musicApi.getTrack(id)
}