package com.example.nike

data class ProductData(
    val name: String,           // Air Jordan XXXVI
    val price: String,          // US$185
    val coverImg: Int,          // R.drawable.air_jordan (이미지 리소스 ID)
    val tag: String = "",
    val desc: String? = null,   // 상세 설명
    val colors: String? = null,

    // 💡 4주차 위시리스트 미션을 위해 추가된 핵심 변수 (반드시 var)
    var isLiked: Boolean = false
)