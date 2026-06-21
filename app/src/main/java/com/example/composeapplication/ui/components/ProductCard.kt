package com.example.composeapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.data.Product

@Composable
fun ProductCard(
    product: Product,
    showHeart: Boolean = true,
    onLikeClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F6F6)), // 연한 회색 배경
        shape = RoundedCornerShape(0.dp) // 나이키 특유의 각진 사각형 스타일
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // 이미지 및 하트 영역
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // 정사각형 비율 유지
            ) {
                // ⭐️ 찐 신발 이미지 연결
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop, // 이미지를 영역에 꽉 차게 자름
                    modifier = Modifier.fillMaxSize()
                )

                // 우상단 하트 아이콘 버튼
                if (showHeart) {
                    IconButton(
                        onClick = onLikeClick,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = if (product.isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Like",
                            tint = if (product.isLiked) Color.Red else Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // BestSeller 오렌지색 태그 표시
            if (product.isBestSeller) {
                Text(
                    text = "BestSeller",
                    color = Color(0xFFFF5722),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }

            // 상품명
            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                maxLines = 2,
                lineHeight = 16.sp
            )

            // 상품 설명 / 컬러 수
            if (product.description.isNotEmpty()) {
                Text(
                    text = product.description,
                    color = Color.Gray,
                    fontSize = 11.sp,
                    lineHeight = 14.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // 가격
            Text(
                text = product.price,
                fontWeight = FontWeight.Black,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}