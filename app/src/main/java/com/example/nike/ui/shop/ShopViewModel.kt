package com.example.nike.ui.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.data.model.ProductData
import com.example.nike.domain.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.first

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val repository: WishlistRepository
) : ViewModel() {

    val products = repository.getProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun toggleHeart(product: ProductData) {
        viewModelScope.launch {
            val currentList = repository.getProducts().first().toMutableList()  // 수정
            val index = currentList.indexOfFirst { it.name == product.name }
            if (index != -1) {
                currentList[index].isLiked = !currentList[index].isLiked
                repository.saveProducts(currentList)
            }
        }
    }
}