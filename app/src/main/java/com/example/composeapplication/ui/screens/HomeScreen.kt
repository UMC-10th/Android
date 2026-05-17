package com.example.composeapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.R

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
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

        Spacer(modifier = Modifier.height(24.dp))

        // 피그마의 나이키 메인 이미지
        Image(
            painter = painterResource(id = R.drawable.nike_discover_img), // 본인의 이미지 파일명
            contentDescription = "Main Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
        )
    }
}