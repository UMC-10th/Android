package com.example.nike.core.data.repository

import com.example.nike.R
import com.example.nike.core.data.PreferenceManager
import com.example.nike.core.component.product.ProductData
import com.example.nike.core.data.datasource.local.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CheckoutRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    val checkoutDataFlow: Flow<List<ProductData>> = localDataSource.checkoutDataListFlow

    suspend fun initCheckoutDataIfEmpty() {
        val current = localDataSource.checkoutDataListFlow.first()
        if (current.isEmpty()) {
            localDataSource.saveCheckoutDataList(createDummy())
        }
    }

    suspend fun toggleLike(name: String) {
        localDataSource.toggleLike(name)
    }

    private fun createDummy(): List<ProductData> = listOf(
        ProductData(
            R.drawable.product_image,
            false,
            "Nike Everyday Plus Cushioned",
            "Training Ankle Socks (6 Pairs)",
            "5 Colors",
            "US$10",
            true
        ),
        ProductData(
            R.drawable.product_image,
            false,
            "Nike Elite Crew",
            "Basketball Socks",
            "7 Colors",
            "US$16",
            false
        ),
        ProductData(
            R.drawable.product_image,
            true,
            "Nike Air Force 1 '07",
            "Women's Shoes",
            "5 Colors",
            "US$115",
            false
        ),
        ProductData(
            R.drawable.product_image,
            true,
            "Jordan Essentials",
            "Men's Shoes",
            "2 Colors",
            "US$115",
            false
        ),
        ProductData(
            R.drawable.product_image,
            true,
            "Nike Air Max 97",
            "Men's Shoes",
            "3 Colors",
            "US$175",
            true
        ),
        ProductData(
            R.drawable.product_image,
            false,
            "Nike Sportswear Tech Fleece",
            "Men's Full-Zip Hoodie",
            "4 Colors",
            "US$130",
            false
        ),
        ProductData(
            R.drawable.product_image,
            true,
            "Nike Dunk Low Retro",
            "Men's Shoes",
            "1 Color",
            "US$115",
            true
        ),
        ProductData(
            R.drawable.product_image,
            false,
            "Nike Dri-FIT Adv",
            "Women's Running Tank",
            "2 Colors",
            "US$65",
            false
        ),
        ProductData(
            R.drawable.product_image,
            true,
            "Air Jordan 1 Mid",
            "Men's Shoes",
            "8 Colors",
            "US$125",
            true
        ),
        ProductData(
            R.drawable.product_image,
            false,
            "Nike Pro Warm",
            "Men's Long-Sleeve Top",
            "2 Colors",
            "US$55",
            false
        ),
        ProductData(
            R.drawable.product_image,
            true,
            "Nike Blazer Mid '77",
            "Women's Shoes",
            "5 Colors",
            "US$105",
            false
        ),
        ProductData(
            R.drawable.product_image,
            false,
            "Nike Heritage Waistpack",
            "Bags & Backpacks",
            "1 Color",
            "US$25",
            true
        ),
        ProductData(
            R.drawable.product_image,
            true,
            "Nike Pegasus 40",
            "Men's Road Running Shoes",
            "6 Colors",
            "US$130",
            false
        ),
        ProductData(
            R.drawable.product_image,
            false,
            "Nike Peak Beanie",
            "Hats & Headbands",
            "3 Colors",
            "US$28",
            false
        )
    )
}