package com.example.nike.presentation.purchase.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.nike.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private enum class PurchaseTab(
    val titleResId: Int,
) {
    ALL(R.string.purchase_tab_all),
    TOPS(R.string.purchase_tab_tops),
    SALE(R.string.purchase_tab_sale)
}

@Composable
fun PurchaseTabRow(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
) {
    ScrollableTabRow (
        selectedTabIndex = pagerState.currentPage,
        containerColor = Color.White,
        contentColor = Color.Black,
        divider = {},
        edgePadding = 9.dp,
        indicator = { tabPositions ->
            TabRowDefaults.PrimaryIndicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                width = tabPositions[pagerState.currentPage].width,
                height = 2.dp,
                color = Color.Black,
            )
        }
    ) {
        PurchaseTab.entries.forEachIndexed { index, tab ->
            val isSelected = pagerState.currentPage == index

            Tab(
                selected = isSelected,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                content = {
                    Text(
                        text = stringResource(tab.titleResId),
                        color = if (isSelected) Color.Black else Color(0xFF767676),
                        modifier = Modifier
                            .padding(
                                vertical = 20.dp,
                                horizontal = 24.dp
                            )
                    )
                }
            )
        }
    }
}