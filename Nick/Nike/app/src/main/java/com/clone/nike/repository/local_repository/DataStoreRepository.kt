package com.clone.nike.repository.local_repository

import androidx.datastore.preferences.core.Preferences
import com.clone.nike.ui.purchase.GoodsData
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveGoodsInfo(goodsData: List<GoodsData>, dataStoreType: Preferences.Key<String>)
    fun getGoodsInfo(dataStoreType: Preferences.Key<String>): Flow<MutableList<GoodsData>>
    fun jsonToGson(json: String): MutableList<GoodsData>
}