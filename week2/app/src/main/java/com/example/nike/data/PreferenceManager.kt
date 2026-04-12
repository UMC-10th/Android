package com.example.nike.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "nike_prefs")

class PreferenceManager(private val context: Context) {
    private val gson = Gson()
    private val ITEMS_KEY = stringPreferencesKey("home_data_list")

    // list -> json
    suspend fun saveHomeDataList(list: List<HomeData>) {
        val jsonString = gson.toJson(list)
        context.dataStore.edit { prefs ->
            prefs[ITEMS_KEY] = jsonString
        }
    }

    // json -> list
    val homeDataListFlow: Flow<List<HomeData>> = context.dataStore.data.map { preferences ->
        val jsonString = preferences[ITEMS_KEY] ?: ""
        if (jsonString.isEmpty()) {
            emptyList()
        } else {
            val type = object : TypeToken<List<HomeData>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }
}