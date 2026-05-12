package com.example.nike.presentation.wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.core.component.product.ProductData
import com.example.nike.core.data.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val repository: WishlistRepository
) : ViewModel() {

    private val _wishlist = MutableLiveData<List<ProductData>>()
    val wishlist: LiveData<List<ProductData>> = _wishlist

    init {
        loadWishlist()
    }

    private fun loadWishlist() {
        viewModelScope.launch {
            repository.wishlistFlow.collect { list ->
                _wishlist.postValue(list)
            }
        }
    }

    fun toggleLike(name: String) {
        viewModelScope.launch {
            repository.toggleLike(name)
        }
    }
}