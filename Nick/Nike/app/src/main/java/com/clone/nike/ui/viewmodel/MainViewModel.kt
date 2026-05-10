package com.clone.nike.ui.viewmodel

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.nike.R
import com.clone.nike.repository.local_repository.DataStoreRepository
import com.clone.nike.ui.purchase.GoodsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DataStoreRepository): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    companion object {
        val NEW_GOODS_DATA = stringPreferencesKey("new_goods_data")
        val GOODS_DATA = stringPreferencesKey("goods_data")
    }

    init {
        fetchNewGoods()
        fetchStandardGoods()
    }

    private fun fetchNewGoods() {
        viewModelScope.launch {
            repository.getGoodsInfo(NEW_GOODS_DATA).collect { list ->
                _uiState.update { it.copy(newGoodsDataList = list) }
            }
        }
    }

    private fun fetchStandardGoods() {
        viewModelScope.launch {
            repository.getGoodsInfo(GOODS_DATA).collect { list ->
                _uiState.update { it.copy(goodsDataList = list) }
            }
        }
    }

    fun inputData() {
        viewModelScope.launch {
            if (uiState.value.newGoodsDataList.isEmpty()) {
                val dummy = addDummyData()
                repository.saveGoodsInfo(dummy, NEW_GOODS_DATA)
            }
            if (uiState.value.goodsDataList.isEmpty()) {
                val dummy = addGoodsData()
                repository.saveGoodsInfo(dummy, GOODS_DATA)
            }
        }
    }

    private fun addDummyData(): MutableList<GoodsData> {
        //더미 데이터
        return mutableListOf<GoodsData>().apply {
            add(GoodsData(R.drawable.image_jordan_xxxvi, "Air jordan XXXVI", "shoes", "1 colour", "US$185", false))
            add(GoodsData(R.drawable.image_air_force_1,"Nike Air Force 1 '07","shoes","3 colours","US$115",false))
        }
    }

    private fun addGoodsData(): MutableList<GoodsData> {
        //더미 데이터
        return mutableListOf<GoodsData>().apply {
            add(GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false))
            add(GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false))
            add(GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false))
            add(GoodsData(R.drawable.image_nike_everyday_plus_cushioned,"Nike Everyday Plus Cushioned","Traning Ankle Socks (6 Pairs)","5 Colours", "US$10",false))
        }
    }
}

data class UiState(
    val newGoodsDataList: List<GoodsData> = emptyList(),
    val goodsDataList: List<GoodsData> = emptyList()
)