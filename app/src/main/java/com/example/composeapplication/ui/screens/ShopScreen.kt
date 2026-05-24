package com.example.composeapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.R
import com.example.composeapplication.data.Product
import com.example.composeapplication.ui.components.ProductCard

@Composable
fun ShopScreen() {
    val gridProducts = listOf(
        Product(
            id = 101,
            name = "Nike Everyday Plus",
            price = "US$10",
            imageRes = R.drawable.white_shoe,
            description = "Training Ankle Socks\n5 Colours",
            isLiked = true
        ),
        Product(
            id = 102,
            name = "Nike Elite Crew",
            price = "US$16",
            imageRes = R.drawable.black_shoe,
            description = "Basketball Socks\n7 Colours"
        ),
        Product(
            id = 103,
            name = "Nike Air Force 1 '07",
            price = "US$115",
            imageRes = R.drawable.white_shoe,
            description = "Women's Shoes",
            isBestSeller = true
        ),
        Product(
            id = 104,
            name = "Jordan E Air Force",
            price = "US$115",
            imageRes = R.drawable.black_shoe,
            description = "Men's Shoes",
            isBestSeller = true
        )
    )

    // 💡 고정된 2열 그리드 사용
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 💡 span 속성을 사용하여 상단 타이틀과 카테고리는 2칸(전체)을 모두 차지하도록 설정
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Text(
                    text = "구매하기",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "전체", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Tops & T-Shirts", fontSize = 16.sp, color = Color.Gray)
                    Text(text = "Shoes", fontSize = 16.sp, color = Color.Gray)
                }
            }
        }

        // 💡 미션 요구사항: items() 와 key 사용
        items(
            items = gridProducts,
            key = { product -> product.id }
        ) { product ->
            ProductCard(product = product)
        }
    }
}