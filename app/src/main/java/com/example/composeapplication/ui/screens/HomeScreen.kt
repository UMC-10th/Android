package com.example.composeapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.R
import com.example.composeapplication.data.Product
import com.example.composeapplication.ui.components.ProductCard

@Composable
fun HomeScreen() {
    // 💡 테스트용 임시 데이터 (실제로는 ViewModel 등에서 가져옴)
    val shopProducts = listOf(
        Product(
            id = 11,
            name = "Nike Everyday Plus",
            price = "US$10",
            imageRes = R.drawable.white_shoe,
            description = "Training Ankle Socks",
            isLiked = true
        ),
        Product(
            id = 12,
            name = "Nike Air Force 1",
            price = "US$115",
            imageRes = R.drawable.black_shoe,
            isBestSeller = true
        )
        // ... 나머지 데이터도 동일하게 추가
    )

    // 💡 전체 화면을 감싸던 Column을 LazyColumn으로 교체!
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        // 💡 Modifier.padding 대신 contentPadding을 사용하여 리스트 양끝 여백 처리
        contentPadding = PaddingValues(20.dp),
        // 💡 아이템 사이의 기본 세로 간격을 spacedBy로 쉽게 지정
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // 1. 상단 텍스트 영역 (하나의 단위로 묶을 수 있는 것은 한 item 안에 작성)
        item {
            Column {
                Text(
                    text = "Discover",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "9월 4일 목요일",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        // 2. 메인 배너 이미지 영역
        item {
            Image(
                painter = painterResource(id = R.drawable.nike_discover_img),
                contentDescription = "Main Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
            )
        }

        // 3. 가로 스크롤 레이아웃 (LazyRow 중첩)
        item {
            Column {
                Text(text = "What's new", color = androidx.compose.ui.graphics.Color.Gray, fontSize = 14.sp)
                Text(text = "나이키 최신 상품", fontWeight = FontWeight.Bold, fontSize = 22.sp, modifier = Modifier.padding(bottom = 12.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // 💡 미션 요구사항: items() 사용 및 key 할당으로 상태 안정성 확보
                    items(
                        items = shopProducts,
                        key = { product -> product.id }
                    ) { product ->
                        ProductCard(
                            product = product,
                            showHeart = false,
                            modifier = Modifier.width(200.dp)
                        )
                    }
                }
            }
        }
    }
}