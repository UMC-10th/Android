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

        val clickedShoe = currentList[position]
        currentList[position] = clickedShoe.copy(
            isLiked = !clickedShoe.isLiked
        )

        // 화면에 먼저 즉시 반영
        _uiState.update {
            it.copy(shopShoes = currentList)
        }

        // DataStore에도 저장
        viewModelScope.launch {
            localRepository.saveShopShoes(currentList)

            val wishlist = ArrayList(currentList.filter { it.isLiked })
            localRepository.saveWishlist(wishlist)
        }
    }
}