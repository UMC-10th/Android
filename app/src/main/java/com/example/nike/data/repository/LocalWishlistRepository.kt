package com.example.nike.data.repository

import com.example.nike.DataManager
import com.example.nike.data.model.ProductData
import com.example.nike.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalWishlistRepository @Inject constructor(
    private val dataManager: DataManager
) : WishlistRepository {
    override fun getProducts(): Flow<List<ProductData>> = dataManager.getProducts()
    override suspend fun saveProducts(products: List<ProductData>) = dataManager.saveProducts(products)
}