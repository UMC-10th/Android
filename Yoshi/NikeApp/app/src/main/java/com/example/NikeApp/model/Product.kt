package com.example.NikeApp.model

import androidx.annotation.DrawableRes

/**
 * 상품 데이터 모델
 */
data class Product(
    val id: String,
    val name: String,
    val price: Int,
    @DrawableRes val imageRes: Int? = null,
)
