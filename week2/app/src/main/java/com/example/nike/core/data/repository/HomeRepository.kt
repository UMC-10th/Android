package com.example.nike.core.data.repository

import com.example.nike.R
import com.example.nike.core.data.datasource.local.LocalDataSource
import com.example.nike.core.data.model.HomeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    val homeDataList: Flow<List<HomeData>> = localDataSource.homeDataListFlow

    suspend fun initHomeDataIfEmpty() {
        val current = localDataSource.homeDataListFlow.first()
        if (current.isEmpty()) {
            localDataSource.saveHomeDataList(createDummy())
        }
    }

    private fun createDummy(): List<HomeData> {
        return mutableListOf<HomeData>().apply {
            add(HomeData(R.drawable.newest_item_1, "Air Jordan XXXVI", "US$185"))
            add(HomeData(R.drawable.newest_item_2, "Nike Air Force 1 '07", "US$115"))
            add(HomeData(R.drawable.newest_item_1, "Nike Air Max 270", "US$160"))
            add(HomeData(R.drawable.newest_item_2, "Air Jordan 1 Retro High OG", "US$180"))
            add(HomeData(R.drawable.newest_item_1, "Nike ZoomX Vaporfly Next% 2", "US$250"))
            add(HomeData(R.drawable.newest_item_2, "Nike Dunk Low Retro", "US$115"))
            add(HomeData(R.drawable.newest_item_1, "Nike Air VaporMax Plus", "US$210"))
            add(HomeData(R.drawable.newest_item_2, "Jordan Stay Loyal 2", "US$115"))
            add(HomeData(R.drawable.newest_item_1, "Nike Pegasus 40", "US$130"))
            add(HomeData(R.drawable.newest_item_2, "Nike Blazer Mid '77 Vintage", "US$105"))
            add(HomeData(R.drawable.newest_item_1, "Nike Air Force 1 React", "US$140"))
            add(HomeData(R.drawable.newest_item_2, "Air Jordan 12 Retro", "US$210"))
        }
    }
}