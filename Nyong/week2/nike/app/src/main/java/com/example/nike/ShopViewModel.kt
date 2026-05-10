package com.example.nike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShopUiState())
    val uiState: StateFlow<ShopUiState> = _uiState.asStateFlow()

    init {
        observeShopShoes()
    }

    private fun observeShopShoes() {
        viewModelScope.launch {
            localRepository.getShopShoes().collect { shoes ->
                _uiState.update {
                    it.copy(shopShoes = shoes)
                }
            }
        }
    }

    fun toggleLike(position: Int) {
        val currentList = ArrayList(_uiState.value.shopShoes)

        if (position !in currentList.indices) return

        currentList[position].isLiked = !currentList[position].isLiked

        viewModelScope.launch {
            // 변경된 구매하기 상품 목록 저장
            localRepository.saveShopShoes(currentList)

            // 좋아요가 true인 상품만 위시리스트로 저장
            val wishlist = ArrayList(currentList.filter { it.isLiked })
            localRepository.saveWishlist(wishlist)
        }
    }
}