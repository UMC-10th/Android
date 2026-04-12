package com.example.nike

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 1. "shoe_prefs"라는 이름의 DataStore 메모장을 만듭니다.
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "shoe_prefs")

class ShoeDataStore(private val context: Context) {
    // 2. 마법의 압축기 Gson 준비
    private val gson = Gson()

    // 3. 포스트잇 이름표(Key) 정의
    companion object {
        val SHOP_SHOES_KEY = stringPreferencesKey("shop_shoes") // 구매하기 데이터 이름표
        val HOME_SHOES_KEY = stringPreferencesKey("home_shoes") // 홈 데이터 이름표
        val WISHLIST_KEY = stringPreferencesKey("wishlist_shoes") // 위시리스트 데이터 이름표
    }

    // ==========================================
    // 구매하기(Shop) 데이터 저장 & 불러오기
    // ==========================================
    suspend fun saveShopShoes(shoeList: ArrayList<ShoeData>) {
        context.dataStore.edit { preferences ->
            // 리스트 -> JSON 글자로 압축해서 저장
            val jsonString = gson.toJson(shoeList)
            preferences[SHOP_SHOES_KEY] = jsonString
        }
    }

    fun getShopShoes(): Flow<ArrayList<ShoeData>> {
        return context.dataStore.data.map { preferences ->
            val jsonString = preferences[SHOP_SHOES_KEY]
            if (jsonString != null) {
                // JSON 글자 -> 다시 리스트로 풀기
                val type = object : TypeToken<ArrayList<ShoeData>>() {}.type
                gson.fromJson(jsonString, type)
            } else {
                ArrayList() // 저장된 게 없으면 빈 리스트 반환
            }
        }
    }

    // ==========================================
    // 홈(Home) 데이터 저장 & 불러오기
    // ==========================================
    suspend fun saveHomeShoes(shoeList: ArrayList<ShoeData>) {
        context.dataStore.edit { preferences ->
            val jsonString = gson.toJson(shoeList)
            preferences[HOME_SHOES_KEY] = jsonString
        }
    }

    fun getHomeShoes(): Flow<ArrayList<ShoeData>> {
        return context.dataStore.data.map { preferences ->
            val jsonString = preferences[HOME_SHOES_KEY]
            if (jsonString != null) {
                val type = object : TypeToken<ArrayList<ShoeData>>() {}.type
                gson.fromJson(jsonString, type)
            } else {
                ArrayList()
            }
        }
    }

    // ==========================================
    // 위시리스트(Wishlist) 데이터 저장 & 불러오기
    // ==========================================
    suspend fun saveWishlist(wishlist: ArrayList<ShoeData>) {
        context.dataStore.edit { preferences ->
            val jsonString = gson.toJson(wishlist)
            preferences[WISHLIST_KEY] = jsonString
        }
    }

    fun getWishlist(): Flow<ArrayList<ShoeData>> {
        return context.dataStore.data.map { preferences ->
            val jsonString = preferences[WISHLIST_KEY]
            if (jsonString != null) {
                val type = object : TypeToken<ArrayList<ShoeData>>() {}.type
                gson.fromJson(jsonString, type)
            } else {
                ArrayList()
            }
        }
    }
}