package com.example.nike.core.component.product

data class ProductData (
    val image: Int,
    val isBestSeller: Boolean,
    val name: String,
    val description: String?,
    val color: String?,
    val price: String,
    var isLiked: Boolean?
)