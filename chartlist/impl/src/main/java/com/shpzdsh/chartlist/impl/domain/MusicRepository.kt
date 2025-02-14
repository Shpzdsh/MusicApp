package com.shpzdsh.chartlist.impl.domain

import com.shpzdsh.apilibrary.models.Data
import com.shpzdsh.apilibrary.models.TrackDetailsResponse

interface MusicRepository {
    suspend fun loadChart(): List<Data>
    suspend fun search(query: String): List<Data>
    suspend fun track(id: Long): TrackDetailsResponse
}