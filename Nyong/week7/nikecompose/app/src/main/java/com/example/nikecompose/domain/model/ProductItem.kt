package com.example.nikecompose.domain.model

import androidx.annotation.DrawableRes

data class ProductItem(
    val id: Int,
    @DrawableRes val imageResId: Int,
    val name: String,
    val subTitle: String,
    val colors: String,
    val price: String,
    val isLiked: Boolean = false,
    val badge: String? = null
)