package com.example.nikecompose.data.dummy

import com.example.nikecompose.R
import com.example.nikecompose.domain.model.ProductItem

val productList = listOf(
    ProductItem(
        id = 1,
        imageResId = R.drawable.img_shoe_1,
        name = "Nike Everyday Plus Cushioned",
        subTitle = "Training Ankle Socks (6 Pairs)",
        colors = "5 Colours",
        price = "US$10",
        isLiked = true
    ),
    ProductItem(
        id = 2,
        imageResId = R.drawable.img_shoe_2,
        name = "Nike Elite Crew",
        subTitle = "Basketball Socks",
        colors = "7 Colours",
        price = "US$16",
        isLiked = false
    ),
    ProductItem(
        id = 3,
        imageResId = R.drawable.img_shoe_1,
        name = "Nike Air Force 1 '07",
        subTitle = "Women's Shoes",
        colors = "5 Colours",
        price = "US$115",
        isLiked = false,
        badge = "BestSeller"
    ),
    ProductItem(
        id = 4,
        imageResId = R.drawable.img_shoe_2,
        name = "Jordan ENike Air Force 1 '07ssentials",
        subTitle = "Men's Shoes",
        colors = "2 Colours",
        price = "US$115",
        isLiked = false,
        badge = "BestSeller"
    )
)