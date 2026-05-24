package com.example.composeapplication.data

import androidx.annotation.DrawableRes
data class Product(
    val id: Int,
    val name: String,
    val price: String,
    @DrawableRes val imageRes: Int,
    val description: String = "",
    val isBestSeller: Boolean = false,
    val isLiked: Boolean = false
)