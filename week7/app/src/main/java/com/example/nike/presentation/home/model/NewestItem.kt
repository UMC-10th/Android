package com.example.nike.presentation.home.model

import androidx.annotation.DrawableRes

data class NewestItem(
    val id: Int,
    @get:DrawableRes val imageRes: Int,
    val name: String,
    val price: String,
)