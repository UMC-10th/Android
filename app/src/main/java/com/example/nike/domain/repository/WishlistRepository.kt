package com.example.nike.domain.repository

import com.example.nike.data.model.ProductData
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {
    fun getProducts(): Flow<List<ProductData>>
    suspend fun saveProducts(products: List<ProductData>)
}