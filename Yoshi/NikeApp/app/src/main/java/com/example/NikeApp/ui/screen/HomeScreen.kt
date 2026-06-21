package com.example.NikeApp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.NikeApp.R
import com.example.NikeApp.model.SampleProducts
import com.example.NikeApp.ui.component.ProductCard
import com.example.NikeApp.ui.theme.NikeAppTheme
import com.example.NikeApp.ui.theme.NikeBlack
import com.example.NikeApp.ui.theme.NikeGray

/**
 * 홈 화면
 */
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // 홈 화면에 5개의 상품만 보여주기
    val homeProducts = remember { SampleProducts.take(5) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Text(
                text = "Discover",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = NikeBlack,
                modifier = Modifier.padding(horizontal = 20.dp),
            )
        }
        item {
            Text(
                text = "9월 4일 목요일",
                style = MaterialTheme.typography.bodyMedium,
                color = NikeGray,
                modifier = Modifier.padding(horizontal = 20.dp),
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.home_img),
                contentDescription = "홈 메인 이미지",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
        }
        item {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "What's new",
                fontSize = 13.sp,
                color = NikeGray,
                modifier = Modifier.padding(horizontal = 20.dp),
            )
            Text(
                text = "나이키 최신 상품",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = NikeBlack,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 2.dp),
            )
        }

        // LazyRow를 사용해 수평으로 스크롤
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(
                    items = homeProducts,
                    key = { product -> product.id },
                ) { product ->
                    ProductCard(
                        product = product,
                        modifier = Modifier.width(280.dp),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    NikeAppTheme { HomeScreen() }
}
