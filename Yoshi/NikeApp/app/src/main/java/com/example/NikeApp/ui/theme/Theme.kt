package com.example.NikeApp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val NikeColorScheme = lightColorScheme(
    primary = NikeBlack,
    onPrimary = NikeWhite,
    background = NikeWhite,
    onBackground = NikeBlack,
    surface = NikeWhite,
    onSurface = NikeBlack,
    secondary = NikeGray,
)

@Composable
fun NikeAppTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = NikeColorScheme,
        typography = NikeTypography,
        content = content,
    )
}
