package com.example.nike.presentation.wishlist

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nike.R
import com.example.nike.core.designsystem.component.ProductGrid
import com.example.nike.core.designsystem.theme.NikeTheme
import com.example.nike.presentation.wishlist.component.WishlistItemCard

@Composable
fun WishlistRoute(
    modifier: Modifier = Modifier,
) {
    WishlistScreen(
        modifier = modifier,
    )
}

@Composable
private fun WishlistScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 28.dp
            ),
    ) {
        Text(
            text = stringResource(R.string.wishlist_title),
            color = Color.Black,
            fontSize = 28.sp,
            modifier = Modifier
                .padding(
                    vertical = 16.dp,
                    horizontal = 24.dp
                )
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProductGrid(
            items = dummyWishlist,
            key = { it.id },
        ) { item ->
            WishlistItemCard(item)
        }
    }
}

// UI 작업용 더미데이터 선언, 추후 이동
data class WishlistItem(
    val id: Int,
    @get:DrawableRes val imageRes: Int,
    val name: String,
    val subName: String,
    val colours: Int,
    val price: String,
)

private val dummyWishlist = listOf(
    WishlistItem(1, R.drawable.img_shoes_1, "Nike Air Force 1 '07", "Women's Shoes", 5, "US$115"),
    WishlistItem(
        2,
        R.drawable.img_socks_1,
        "Nike Everyday Plus Cushioned",
        "Training Ankle Socks (6 Pairs)",
        5,
        "US$10"
    ),
    WishlistItem(3, R.drawable.img_shoes_2, "Air Jordan XXXVI", "Men's Shoes", 2, "US$185"),
    WishlistItem(4, R.drawable.img_top_1, "Nike Sportswear Club", "Men's T-Shirt", 4, "US$35"),
    WishlistItem(5, R.drawable.img_shoes_3, "Nike Dunk Low", "Men's Shoes", 6, "US$110"),
)

@Preview(showBackground = true)
@Composable
private fun WishlistScreenPreview() {
    NikeTheme {
        WishlistScreen()
    }
}