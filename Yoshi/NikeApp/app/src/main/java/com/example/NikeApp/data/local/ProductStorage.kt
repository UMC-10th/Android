package com.example.NikeApp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.NikeApp.data.model.ProductData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore 인스턴스 생성
val Context.dataStore by preferencesDataStore(name = "product_store")

class ProductStorage(private val context: Context) {

    private val gson = Gson()

    companion object {
        val PRODUCT_LIST_KEY = stringPreferencesKey("product_list")
    }

    // 상품 목록 저장하기
    suspend fun saveProducts(products: List<ProductData>) {
        val jsonString = gson.toJson(products)
        context.dataStore.edit { preferences ->
            preferences[PRODUCT_LIST_KEY] = jsonString
        }
    }

    // 상품 목록 가져오기 (Flow로 실시간 감지)
    fun getProducts(): Flow<List<ProductData>> {
        return context.dataStore.data.map { preferences ->
            val jsonString = preferences[PRODUCT_LIST_KEY]
            if (jsonString != null) {
                val type = object : TypeToken<List<ProductData>>() {}.type
                gson.fromJson(jsonString, type)
            } else {
                emptyList()
            }
        }
    }
}
