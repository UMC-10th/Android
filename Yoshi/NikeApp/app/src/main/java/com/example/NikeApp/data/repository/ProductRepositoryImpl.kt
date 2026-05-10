package com.example.NikeApp.data.repository

import com.example.NikeApp.data.local.ProductStorage
import com.example.NikeApp.data.model.ProductData
import com.example.NikeApp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Local Repository: DataStore(ProductStorage)에 호출 위임
class ProductRepositoryImpl @Inject constructor(
    private val productStorage: ProductStorage
) : ProductRepository {

    override fun getProducts(): Flow<List<ProductData>> =
        productStorage.getProducts()

    override suspend fun saveProducts(products: List<ProductData>) {
        productStorage.saveProducts(products)
    }
}
