package com.example.nike

data class ProductData(
    val name: String,       // Air Jordan XXXVI
    val price: String,      // US$185
    val coverImg: Int,       // R.drawable.air_jordan (이미지 리소스 ID)
    val tag: String = "",
    val desc: String? = null,   // 상세 설명 (예: Women's Shoes)
    val colors: String? = null
)