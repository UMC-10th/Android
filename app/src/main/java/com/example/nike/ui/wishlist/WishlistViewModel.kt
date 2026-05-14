package com.example.nike.ui.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.data.model.ProductData
import com.example.nike.domain.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val repository: WishlistRepository
) : ViewModel() {

    val wishlist = repository.getProducts()
        .map { it.filter { product -> product.isLiked } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun removeFromWishlist(product: ProductData) {
        viewModelScope.launch {
            val currentList = wishlist.value.toMutableList()
            val index = currentList.indexOfFirst { it.name == product.name }
            if (index != -1) {
                currentList[index].isLiked = false
                repository.saveProducts(currentList)
            }
        }
    }
}