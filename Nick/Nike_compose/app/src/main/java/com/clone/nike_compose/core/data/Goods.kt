package com.clone.nike_compose.core.data

data class Goods(
    val goodsId: Int,
    val goodsImgResId: Int,
    val goodsName: String,
    val category: String,
    val numberOfColour: String,
    val goodsPrice: String,
    var isWished: Boolean
)