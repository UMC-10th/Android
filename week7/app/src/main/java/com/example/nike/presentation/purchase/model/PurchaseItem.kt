package com.example.nike.presentation.purchase.model

import androidx.annotation.DrawableRes

enum class PurchaseCategory { TOPS, SOCKS, SHOES }

data class PurchaseItem(
    val id: Int,
    @get:DrawableRes val imageRes: Int,
    val name: String,
    val subName: String,
    val colours: Int,
    val price: String,
    val category: PurchaseCategory,
    val isBestSeller: Boolean = false,
    val isOnSale: Boolean = false,
)
