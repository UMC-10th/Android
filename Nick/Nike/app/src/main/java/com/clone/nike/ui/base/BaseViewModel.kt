package com.clone.nike.ui.base

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<STATE>(initialPageSTATE: STATE): ViewModel() {
    protected val _uiState = MutableStateFlow(initialPageSTATE)
    val uiState: StateFlow<STATE> = _uiState.asStateFlow()


    companion object {
        val NEW_GOODS_DATA = stringPreferencesKey("new_goods_data")
        val GOODS_DATA = stringPreferencesKey("goods_data")
    }
}