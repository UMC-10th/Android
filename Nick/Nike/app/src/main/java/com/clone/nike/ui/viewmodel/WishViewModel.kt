package com.clone.nike.ui.viewmodel

import androidx.datastore.preferences.core.stringPreferencesKey
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
class WishViewModel @Inject constructor(private val repository: DataStoreRepository): BaseViewModel<WishUiState>(
    WishUiState()
) {
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