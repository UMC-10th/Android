package com.example.NikeApp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.NikeApp.R
import com.example.NikeApp.ui.theme.NikeAppTheme
import com.example.NikeApp.ui.theme.NikeBlack
import com.example.NikeApp.ui.theme.NikeGray

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        Text(
            text = "Discover",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = NikeBlack,
        )
        Text(
            text = "9월 4일 목요일",
            style = MaterialTheme.typography.bodyMedium,
            color = NikeGray,
            modifier = Modifier.padding(top = 4.dp),
        )

        Image(
            painter = painterResource(id = R.drawable.home_img),
            contentDescription = "홈 메인 이미지",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    NikeAppTheme { HomeScreen() }
}
