package com.example.composeapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShopScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "구매하기",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ⭐️ 핵심 수정 부분: 간격 규칙과 너비 가이드를 명확히 지정합니다.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp), // 글자 사이 여백 16dp
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "전체",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Tops & T-Shirts",
                fontSize = 16.sp,
                color = androidx.compose.ui.graphics.Color.Gray
            )
            Text(
                text = "Shoes",
                fontSize = 16.sp,
                color = androidx.compose.ui.graphics.Color.Gray
            )
        }
    }
}