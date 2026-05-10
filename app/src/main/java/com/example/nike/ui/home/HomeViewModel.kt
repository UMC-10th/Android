package com.example.nike.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.R
import com.example.nike.data.model.ProductData
import com.example.nike.domain.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WishlistRepository
) : ViewModel() {

    val products = repository.getProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun initDummyDataIfEmpty() {
        viewModelScope.launch {
            if (products.value.isEmpty()) {
                val dummyList = listOf(
                    ProductData("Nike Air Force 1 '07 (White)", "US$115", R.drawable.air_jordan, "BestSeller"),
                    ProductData("Nike Everyday Plus (Pack A)", "US$10", R.drawable.air_jordan, ""),
                    ProductData("Jordan ENike Air Force", "US$115", R.drawable.air_jordan, "BestSeller"),
                    ProductData("Nike Elite Crew (Black)", "US$16", R.drawable.air_jordan, ""),
                    ProductData("Nike Everyday Plus (Pack B)", "US$10", R.drawable.air_jordan, "", "Training Ankle Socks", "5 Colours"),
                    ProductData("Nike Dunk Low (Retro)", "US$110", R.drawable.air_jordan, ""),
                    ProductData("Nike Air Max (97)", "US$130", R.drawable.air_jordan, "BestSeller"),
                    ProductData("Nike Everyday Plus (Pack C)", "US$10", R.drawable.air_jordan, "", "Training Ankle Socks", "5 Colours")
                )
                repository.saveProducts(dummyList)
            }
        }
    }

    fun toggleHeart(product: ProductData) {
        viewModelScope.launch {
            val currentList = products.value.toMutableList()
            val index = currentList.indexOfFirst { it.name == product.name }
            if (index != -1) {
                currentList[index].isLiked = !currentList[index].isLiked
                repository.saveProducts(currentList)
            }
        }
    }
}