package com.example.NikeApp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.NikeApp.model.SampleProducts
import com.example.NikeApp.ui.component.ProductCard
import com.example.NikeApp.ui.theme.NikeAppTheme
import com.example.NikeApp.ui.theme.NikeBlack
import com.example.NikeApp.ui.theme.NikeGray

/**
 * 위시리스트 화면
 * 전체 상품 중 [wishlistIds] 에 포함된 것만 LazyVerticalGrid 로 2 x N 형태로 표시
 * 카드의 하트를 다시 누르면 위시리스트에서 제거
 */
@Composable
fun WishlistScreen(
    wishlistIds: Set<String>,
    onToggleWishlist: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val wishedProducts = SampleProducts.filter { it.id in wishlistIds }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        // 제목은 전체 열을 차지하도록 span 지정
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "위시리스트",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = NikeBlack,
            )
        }

        if (wishedProducts.isEmpty()) {
            // 빈 상태 메시지도 전체 행 차지
            item(span = { GridItemSpan(maxLineSpan) }) {
                EmptyWishlistMessage()
            }
        } else {
            items(
                items = wishedProducts,
                key = { product -> product.id },
            ) { product ->
                ProductCard(
                    product = product,
                    modifier = Modifier.fillMaxWidth(),
                    showWishButton = true,
                    isWished = true,
                    onWishClick = { onToggleWishlist(product.id) },
                )
            }
        }
    }
}

@Composable
private fun EmptyWishlistMessage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "아직 위시한 상품이 없습니다",
            fontSize = 14.sp,
            color = NikeGray,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WishlistScreenPreview() {
    NikeAppTheme {
        WishlistScreen(
            wishlistIds = setOf("p1", "p3"),
            onToggleWishlist = {},
        )
    }
}
