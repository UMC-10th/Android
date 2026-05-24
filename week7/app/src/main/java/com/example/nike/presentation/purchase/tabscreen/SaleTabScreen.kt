package com.example.nike.presentation.purchase.tabscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nike.presentation.purchase.ProductItem
import com.example.nike.presentation.purchase.component.ProductGrid

@Composable
fun SaleTabScreen(
    items: List<ProductItem>,
    modifier: Modifier = Modifier,
) {
    ProductGrid(
        items = items.filter {
            it.isOnSale
        },
        modifier = modifier.fillMaxSize()
    )
}