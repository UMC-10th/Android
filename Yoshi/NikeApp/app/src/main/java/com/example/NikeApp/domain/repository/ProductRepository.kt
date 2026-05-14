package com.example.NikeApp.domain.repository

import com.example.NikeApp.data.model.ProductData
import kotlinx.coroutines.flow.Flow

// 상품 데이터(Local) 접근
interface ProductRepository {
    fun getProducts(): Flow<List<ProductData>>
    suspend fun saveProducts(products: List<ProductData>)
}
