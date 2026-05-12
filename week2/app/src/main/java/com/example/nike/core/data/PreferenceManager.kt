package com.example.nike.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.nike.core.component.product.ProductData
import com.example.nike.core.data.model.HomeData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "nike_prefs")

class PreferenceManager(private val context: Context) {
    private val gson = Gson()

    companion object {
        // 홈화면 최신상품
        private val HOME_ITEMS_KEY = stringPreferencesKey("home_data_list")

        // 구매하기
        private val CHECKOUT_ITEMS_KEY = stringPreferencesKey("checkout_data_list")
    }

    // ====== 홈화면 ======
    // list -> json
    suspend fun saveHomeDataList(list: List<HomeData>) {
        val jsonString = gson.toJson(list)
        context.dataStore.edit { prefs ->
            prefs[HOME_ITEMS_KEY] = jsonString
        }
    }
    // json -> list
    val homeDataListFlow: Flow<List<HomeData>> = context.dataStore.data.map { preferences ->
        val jsonString = preferences[HOME_ITEMS_KEY] ?: ""
        if (jsonString.isEmpty()) {
            emptyList()
        } else {
            val type = object : com.google.gson.reflect.TypeToken<List<HomeData>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }

    // ====== 구매하기 =======
    suspend fun saveCheckoutDataList(list: List<ProductData>) {
        val jsonString = gson.toJson(list)
        context.dataStore.edit { prefs ->
            prefs[CHECKOUT_ITEMS_KEY] = jsonString
        }
    }
    val checkoutDataListFlow: Flow<List<ProductData>> = context.dataStore.data.map { preferences ->
        val jsonString = preferences[CHECKOUT_ITEMS_KEY] ?: ""
        if (jsonString.isEmpty()) {
            emptyList()
        } else {
            val type = object : com.google.gson.reflect.TypeToken<List<ProductData>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }

    // ====== 위시리스트 ======
    val wishlistItemFlow: Flow<List<ProductData>> = checkoutDataListFlow.map { checkoutList ->
        checkoutList.filter { it.isLiked == true }
    }
    suspend fun toggleLike(productName: String) {
        val currentList = checkoutDataListFlow.first()
        val updatedList = currentList.map { item ->
            if (item.name == productName) {
                item.copy(isLiked = !(item.isLiked ?: false))
            } else {
                item
            }
        }
        saveCheckoutDataList(updatedList)
    }
}