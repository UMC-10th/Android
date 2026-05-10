package com.example.NikeApp.ui.purchase

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

data class PurchaseUiState(
    val products: List<ProductData> = emptyList()
)

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PurchaseUiState())
    val uiState: StateFlow<PurchaseUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val products = productRepository.getProducts().first()
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
}
