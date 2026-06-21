package com.example.NikeApp.model

import com.example.NikeApp.R

/**
 * 홈/구매하기 화면에서 사용하는 더미 상품 목록
 * 홈 화면: 5개
 * 구매하기: 8개
 */
val SampleProducts: List<Product> = listOf(
    Product(id = "p1", name = "Air Force 1 '07", price = 150, imageRes = R.drawable.air_force_1_07),
    Product(id = "p2", name = "Air Max 90", price = 200, imageRes = R.drawable.air_max_90),
    Product(id = "p3", name = "Dunk Low Retro", price = 175, imageRes = R.drawable.dunk_low_retro),
    Product(id = "p4", name = "Air Jordan 1 Mid", price = 220, imageRes = R.drawable.air_jordan_1_mid),
    Product(id = "p5", name = "Cortez Basic", price = 100, imageRes = R.drawable.cortez_basic),
    Product(id = "p6", name = "Pegasus 41", price = 130, imageRes = R.drawable.pegasus_41),
    Product(id = "p7", name = "Blazer Mid '77", price = 90, imageRes = R.drawable.blazer_mid_77),
    Product(id = "p8", name = "Sportswear Crew Socks", price = 15, imageRes = R.drawable.sportswear_crew_socks),
)

/** 가격 형식 변환 */
fun Product.formattedPrice(): String = "US$%,d".format(price)
