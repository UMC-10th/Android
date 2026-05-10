package com.clone.nike.repository.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.clone.nike.repository.local_repository.DataStoreRepository
import com.clone.nike.ui.purchase.GoodsData
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson
): DataStoreRepository {

    override suspend fun saveGoodsInfo(goodsData: List<GoodsData>, dataStoreType: Preferences.Key<String>) {
        dataStore.edit { preferences ->
            val jsonString = gson.toJson(goodsData)
            preferences[dataStoreType] = jsonString
        }
    }

    override fun getGoodsInfo(dataStoreType: Preferences.Key<String>): Flow<MutableList<GoodsData>> {
        return dataStore.data.map { preferences ->
            val json = preferences[dataStoreType] ?: "[]"
            jsonToGson(json)
        }
    }

    override fun jsonToGson(json: String): MutableList<GoodsData> {
        val goodsDataList = gson.fromJson(json, Array<GoodsData>::class.java).toMutableList()
        return goodsDataList
    }
}