package com.example.nike.core.data.datasource.local

import com.example.nike.core.component.product.ProductData
import com.example.nike.core.data.PreferenceManager
import com.example.nike.core.data.model.HomeData
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val preferenceManager: PreferenceManager
) {
    // Home
    val homeDataListFlow = preferenceManager.homeDataListFlow
    suspend fun saveHomeDataList(list: List<HomeData>) = preferenceManager.saveHomeDataList(list)

    // Checkout
    val checkoutDataListFlow = preferenceManager.checkoutDataListFlow
    suspend fun saveCheckoutDataList(list: List<ProductData>) = preferenceManager.saveCheckoutDataList(list)

    // Wishlist
    val wishlistItemFlow = preferenceManager.wishlistItemFlow
    suspend fun toggleLike(name: String) = preferenceManager.toggleLike(name)
}