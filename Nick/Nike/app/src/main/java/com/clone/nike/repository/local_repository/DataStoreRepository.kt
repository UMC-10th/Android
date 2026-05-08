package com.clone.nike.repository.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.clone.nike.ui.purchase.GoodsData
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "goods_data")

class DataStoreRepository(private val context: Context) {
    private val gson = Gson()

    suspend fun saveGoodsInfo(goodsData: MutableList<GoodsData>, dataStore: Preferences.Key<String>) {
        context.dataStore.edit { setting ->
            val jsonString = gson.toJson(goodsData)
            setting[dataStore] = jsonString
        }
    }

    fun getGoodsInfo(dataStore: Preferences.Key<String>): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[dataStore] ?: "[]"
        }
    }

    fun jsonToGson(json: String): MutableList<GoodsData> {
        val goodsDataList = gson.fromJson(json, Array<GoodsData>::class.java).toMutableList()
        return goodsDataList
    }
}