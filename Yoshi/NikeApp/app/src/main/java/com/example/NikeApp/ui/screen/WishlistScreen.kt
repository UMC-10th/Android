package com.example.NikeApp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.NikeApp.ui.theme.NikeAppTheme
import com.example.NikeApp.ui.theme.NikeBlack

@Composable
fun WishlistScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        Text(
            text = "위시리스트",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = NikeBlack,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WishlistScreenPreview() {
    NikeAppTheme { WishlistScreen() }
}
