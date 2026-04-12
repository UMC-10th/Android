package com.example.nike

data class ShoeData(
    val imageResId: Int,   // 신발 이미지 (R.drawable.img_shoe_1)
    val name: String,      // 제품명 (Nike Everyday Plus Cushioned)
    val subTitle: String,  // 서브 설명 (Training Ankle Socks)
    val colors: String,    // 색상 수 (5 Colours)
    val price: String,      // 가격 (US$10)
    var isLiked: Boolean = false // 추가: 위시리스트용 하트 상태 (기본값은 안 눌림)
)