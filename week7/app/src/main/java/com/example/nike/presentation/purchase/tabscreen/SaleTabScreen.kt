package com.example.nike.presentation.purchase.tabscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nike.presentation.purchase.ProductItem
import com.example.nike.core.designsystem.component.ProductGrid
import com.example.nike.presentation.purchase.component.PurchaseItemCard

@Composable
fun SaleTabScreen(
    items: List<ProductItem>,
    modifier: Modifier = Modifier,
) {
    ProductGrid(
        items = items.filter {
            it.isOnSale
        },
        key = { it.id },
        modifier = modifier.fillMaxSize()
    ) { item ->
        PurchaseItemCard(item)
    }
}