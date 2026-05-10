package com.clone.nike.ui.viewmodel

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.nike.repository.local_repository.DataStoreRepository
import com.clone.nike.ui.base.BaseViewModel
import com.clone.nike.ui.purchase.GoodsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(private val repository: DataStoreRepository): BaseViewModel<PurchaseUiState>(
    PurchaseUiState()
) {
    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                repository.getGoodsInfo(GOODS_DATA).collect { goodsDataList ->

                    //로딩화면 구현을 위해 일부로 딜레이
                    delay(1000)
                    _uiState.update { it.copy(isLoading = false, goodsDataList = goodsDataList) }
                }
            }
            catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
            }
        }
    }

    fun saveGoodsInfo(goodsList: MutableList<GoodsData>) {
        viewModelScope.launch {
            repository.saveGoodsInfo(goodsList, GOODS_DATA)
        }
    }

}

data class PurchaseUiState(
    val isLoading: Boolean = false,
    val goodsDataList: List<GoodsData> = emptyList(),
    val errorMessage: String? = null
)