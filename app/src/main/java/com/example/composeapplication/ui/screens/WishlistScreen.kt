package com.example.composeapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.R
import com.example.composeapplication.data.Product
import com.example.composeapplication.ui.components.ProductCard

@Composable
fun WishlistScreen() {
    val wishlistProducts = listOf(
        Product(
            id = 201,
            name = "Air Jordan 1 Mid",
            price = "US$125",
            imageRes = R.drawable.white_shoe
        ),
        Product(
            id = 202,
            name = "Nike Everyday Plus",
            price = "US$10",
            imageRes = R.drawable.black_shoe,
            description = "Training Ankle Socks"
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 상단 "위시리스트" 텍스트 영역 (전체 열 차지)
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "위시리스트",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        // 아이템 출력 영역
        items(
            items = wishlistProducts,
            key = { product -> product.id }
        ) { product ->
            ProductCard(
                product = product,
                showHeart = false // 위시리스트에서는 하트를 안 보여줌
            )
        }
    }
}