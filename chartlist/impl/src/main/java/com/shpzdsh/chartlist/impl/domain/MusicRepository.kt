package com.shpzdsh.chartlist.impl.domain

import com.shpzdsh.apilibrary.models.Data

interface MusicRepository {
    suspend fun loadChart(): List<Data>
}