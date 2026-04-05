package com.example.umc10th_android_week3

data class ProductData(
    val productImage: Int,    // 이미지 ID
    val productName: String,  // 상품명
    val description: String,  // 설명
    val colorCount: String,   // 색상 수
    val price: String         // 가격
)