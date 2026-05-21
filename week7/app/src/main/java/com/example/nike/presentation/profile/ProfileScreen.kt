package com.example.nike.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.nike.core.designsystem.theme.NikeTheme

@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
) {
    ProfileScreen(
        modifier = modifier,
    )
}

@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "프로필 화면",
            color = Color.Black,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    NikeTheme {
        ProfileScreen()
    }
}