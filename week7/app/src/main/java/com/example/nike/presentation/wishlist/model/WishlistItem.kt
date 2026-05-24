package com.example.nike.presentation.wishlist.model

import androidx.annotation.DrawableRes

data class WishlistItem(
    val id: Int,
    @get:DrawableRes val imageRes: Int,
    val name: String,
    val subName: String,
    val colours: Int,
    val price: String,
)