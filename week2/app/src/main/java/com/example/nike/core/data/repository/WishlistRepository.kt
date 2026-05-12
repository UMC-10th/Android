package com.example.nike.core.data.repository

import com.example.nike.core.component.product.ProductData
import com.example.nike.core.data.PreferenceManager
import com.example.nike.core.data.datasource.local.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    val wishlistFlow: Flow<List<ProductData>> = localDataSource.wishlistItemFlow

    suspend fun toggleLike(name: String) {
        localDataSource.toggleLike(name)
    }
}