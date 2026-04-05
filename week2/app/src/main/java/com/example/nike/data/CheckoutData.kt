package com.example.nike.data

data class CheckoutData (
    val image: Int,
    val isBestSeller: Boolean,
    val name: String,
    val description: String,
    val color: String,
    val price: String,
    var isLiked: Boolean
)