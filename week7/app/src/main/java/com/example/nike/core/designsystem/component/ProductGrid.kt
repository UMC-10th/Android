package com.example.nike.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> ProductGrid(
    items: List<T>,
    key: (T) -> Any, // T가 ID를 보장하지 않으므로 외부에서 주입
    modifier: Modifier = Modifier,
    itemContent: @Composable (T) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(
            vertical = 12.dp,
            horizontal = 20.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        items(
            items,
            key = key
        ) { item ->
            itemContent(item)
        }
    }
}