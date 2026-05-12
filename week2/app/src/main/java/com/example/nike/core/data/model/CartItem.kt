package com.example.nike.core.data.model

data class CartItem(
    val id: Int,
    val productName: String,
    val price: Int,
    val quantity: Int,
    val imageUrl: String
)