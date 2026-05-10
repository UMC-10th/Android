package com.example.NikeApp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.NikeApp.R
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

data class HomeUiState(
    val products: List<ProductData> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val saved = productRepository.getProducts().first()

            // 최초 진입 시 더미 데이터 저장
            val products = if (saved.isEmpty()) {
                val dummy = defaultProducts()
                productRepository.saveProducts(dummy)
                dummy
            } else {
                saved
            }

            _uiState.update { it.copy(products = products) }
        }
    }

    fun toggleLike(product: ProductData) {
        viewModelScope.launch {
            val current = _uiState.value.products.toMutableList()
            val index = current.indexOfFirst { it.productName == product.productName }
            if (index == -1) return@launch

            current[index] = product
            productRepository.saveProducts(current)
            _uiState.update { it.copy(products = current) }
        }
    }

    private fun defaultProducts(): List<ProductData> = listOf(
        ProductData(R.drawable.ic_launcher_background, "Air Jordan XXXVI", "Basketball Shoes", "3 Colours", "US\$185"),
        ProductData(R.drawable.ic_launcher_background, "Air Jordan 1 Mid", "Shoes", "1 Colour", "US\$125"),
        ProductData(R.drawable.ic_launcher_background, "Nike Air Force 1 '07", "Women's Shoes", "5 Colours", "US\$115"),
        ProductData(R.drawable.ic_launcher_background, "Nike Everyday Plus Cushioned", "Training Ankle Socks", "5 Colours", "US\$10"),
        ProductData(R.drawable.ic_launcher_background, "Nike Elite Crew", "Basketball Socks", "7 Colours", "US\$16"),
        ProductData(R.drawable.ic_launcher_background, "Jordan ENike Air Force", "Men's Shoes", "2 Colours", "US\$115")
    )
}
