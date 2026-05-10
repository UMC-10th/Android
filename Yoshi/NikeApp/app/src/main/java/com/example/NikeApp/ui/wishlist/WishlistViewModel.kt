package com.example.NikeApp.ui.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.NikeApp.data.model.ProductData
import com.example.NikeApp.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WishlistUiState(
    val likedProducts: List<ProductData> = emptyList()
)

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()

    init {
        loadLikedProducts()
    }

    private fun loadLikedProducts() {
        viewModelScope.launch {
            val all = productRepository.getProducts().first()
            val liked = all.filter { it.isLiked }
            _uiState.update { it.copy(likedProducts = liked) }
        }
    }

    // 위시리스트에서 하트 토글: 전체 목록 동기화 + 위시 화면 갱신
    fun toggleLike(product: ProductData) {
        viewModelScope.launch {
            val fullList = productRepository.getProducts().first().toMutableList()
            val index = fullList.indexOfFirst { it.productName == product.productName }
            if (index == -1) return@launch

            fullList[index] = product
            productRepository.saveProducts(fullList)

            _uiState.update {
                it.copy(likedProducts = fullList.filter { p -> p.isLiked })
            }
        }
    }
}
