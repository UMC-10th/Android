package com.clone.nike.ui.wish

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.nike.repository.local_repository.DataStoreRepository
import com.clone.nike.ui.purchase.GoodsData
import com.clone.nike.ui.purchase.PurchaseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishViewModel @Inject constructor(private val repository: DataStoreRepository): ViewModel() {
    private val _uiState = MutableStateFlow(WishUiState())
    val uiState: StateFlow<WishUiState> = _uiState.asStateFlow()

    val GOODS_DATA = stringPreferencesKey("goods_data")

    init {
        viewModelScope.launch {
            repository.getGoodsInfo(GOODS_DATA).collect { goodsDataList ->
                _uiState.update { it.copy(goodsDataList = goodsDataList) }
            }
        }
    }

    fun listFilter(goodsDataList: MutableList<GoodsData>): MutableList<GoodsData> {
        //goodsList에서 wishList로 변환
        return goodsDataList.filter { it.isWished }.toMutableList()
    }
}

data class WishUiState(
    val goodsDataList: List<GoodsData> = emptyList()
)