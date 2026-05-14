package com.example.nike // 본인 패키지명 확인!

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.nike.data.model.ProductData
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore 싱글톤 인스턴스 생성
val Context.dataStore by preferencesDataStore(name = "nike_data_store")

class DataManager(private val context: Context) {
    private val gson = Gson()
    private val KEY = stringPreferencesKey("nike_master_list")

    suspend fun saveProducts(products: List<ProductData>) {
        val json = gson.toJson(products)
        context.dataStore.edit { it[KEY] = json }
    }

    fun getProducts(): Flow<List<ProductData>> {
        return context.dataStore.data.map { prefs ->
            val json = prefs[KEY] ?: ""
            if (json.isEmpty()) emptyList()
            else gson.fromJson(json, Array<ProductData>::class.java).toList()
        }
    }
}