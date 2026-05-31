package com.example.nike.presentation.purchase.tabscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nike.core.designsystem.component.ProductGrid
import com.example.nike.presentation.purchase.component.PurchaseItemCard
import com.example.nike.presentation.purchase.model.PurchaseItem

@Composable
fun SaleTabScreen(
    items: List<PurchaseItem>,
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