package com.example.nike.presentation.wishlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nike.core.designsystem.theme.NikeTheme

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
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 28.dp
            ),
        contentAlignment = Alignment.TopStart
    ) {
        Text(
            text = "위시리스트",
            color = Color.Black,
            fontSize = 28.sp,
            modifier = Modifier
                .padding(
                    vertical = 16.dp,
                    horizontal = 24.dp
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WishlistScreenPreview() {
    NikeTheme {
        WishlistScreen()
    }
}