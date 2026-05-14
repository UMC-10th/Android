package com.clone.nike.ui.viewmodel

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.nike.repository.local_repository.DataStoreRepository
import com.clone.nike.ui.base.BaseViewModel
import com.clone.nike.ui.purchase.GoodsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DataStoreRepository,
    private val savedStateHandle: SavedStateHandle
): BaseViewModel<HomeUiState>(HomeUiState()) {

    val title: String

    init {
        viewModelScope.launch {
            repository.getGoodsInfo(NEW_GOODS_DATA).collect { goodsDataString ->
                _uiState.update { it.copy(goodsDataList = goodsDataString) }
            }
        }

        title = savedStateHandle["title"] ?: "invalid title"
    }
}

data class HomeUiState(
    val goodsDataList: List<GoodsData> = emptyList()
)
