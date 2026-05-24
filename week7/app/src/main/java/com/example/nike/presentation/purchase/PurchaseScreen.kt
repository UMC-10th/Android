package com.example.nike.presentation.purchase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nike.core.designsystem.theme.NikeTheme
import com.example.nike.presentation.purchase.component.PurchaseTabRow
import com.example.nike.presentation.purchase.tabscreen.AllTabScreen
import com.example.nike.presentation.purchase.tabscreen.SaleTabScreen
import com.example.nike.presentation.purchase.tabscreen.TopsTabScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun PurchaseRoute(
    modifier: Modifier = Modifier,
) {
    PurchaseScreen(
        modifier = modifier,
    )
}

@Composable
private fun PurchaseScreen(
    modifier: Modifier = Modifier,
) {
    val pagerState: PagerState = rememberPagerState(pageCount = {3})
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize(),
    ){
        PurchaseTabRow(pagerState, coroutineScope)

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> AllTabScreen()
                1 -> TopsTabScreen()
                2 -> SaleTabScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PurchaseScreenPreview() {
    NikeTheme {
        PurchaseScreen()
    }
}