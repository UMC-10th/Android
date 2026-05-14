package com.example.nike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()

    init {
        observeWishlist()
    }

    private fun observeWishlist() {
        viewModelScope.launch {
            localRepository.getWishlist().collect { wishlist ->
                _uiState.update {
                    it.copy(wishlistShoes = wishlist)
                }
            }
        }
    }

    fun unlikeFromWishlist(position: Int) {
        val currentWishlist = ArrayList(_uiState.value.wishlistShoes)

        if (position !in currentWishlist.indices) return

        val unlikedShoe = currentWishlist[position]

        viewModelScope.launch {
            // 1. 원본 구매하기 목록 가져오기
            val shopList = localRepository.getShopShoes().first()

            // 2. 위시리스트에서 취소한 상품과 이름이 같은 상품 찾기
            val targetShoe = shopList.find { it.name == unlikedShoe.name }

            // 3. 원본 구매하기 목록의 하트 상태를 false로 변경
            if (targetShoe != null) {
                targetShoe.isLiked = false
            }

            // 4. 변경된 구매하기 목록 저장
            localRepository.saveShopShoes(shopList)

            // 5. 좋아요가 true인 상품만 다시 위시리스트로 저장
            val newWishlist = ArrayList(shopList.filter { it.isLiked })
            localRepository.saveWishlist(newWishlist)

            // 6. 화면에 즉시 반영
            _uiState.update {
                it.copy(wishlistShoes = newWishlist)
            }
        }
    }
}