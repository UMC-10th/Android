package com.example.nike.presentation.purchase

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nike.R
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
    val pagerState: PagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val products = remember { dummyProducts.shuffled() }

    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        PurchaseTabRow(pagerState, coroutineScope)

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> AllTabScreen(items = products)
                1 -> TopsTabScreen(items = products)
                2 -> SaleTabScreen(items = products)
            }
        }
    }
}

// UI 작업용 더미데이터 선언, 추후 이동
enum class ProductCategory { TOPS, SOCKS, SHOES }

data class ProductItem(
    val id: Int,
    @get:DrawableRes val imageRes: Int,
    val name: String,
    val subName: String,
    val colours: Int,
    val price: String,
    val category: ProductCategory,
    val isBestSeller: Boolean = false,
    val isOnSale: Boolean = false,
)

private val dummyProducts = listOf(
    ProductItem(
        1,
        R.drawable.img_top_1,
        "Nike Sportswear Club",
        "Men's T-Shirt",
        4,
        "US$35",
        ProductCategory.TOPS
    ),
    ProductItem(
        2,
        R.drawable.img_top_2,
        "Nike Dri-FIT",
        "Training T-Shirt",
        3,
        "US$40",
        ProductCategory.TOPS,
        isOnSale = true
    ),
    ProductItem(
        3,
        R.drawable.img_top_3,
        "Nike Air",
        "Women's Top",
        5,
        "US$45",
        ProductCategory.TOPS
    ),

    ProductItem(
        4,
        R.drawable.img_socks_1,
        "Nike Everyday Plus Cushioned",
        "Training Ankle Socks (6 Pairs)",
        5,
        "US$10",
        ProductCategory.SOCKS
    ),
    ProductItem(
        5,
        R.drawable.img_socks_2,
        "Nike Elite Crew",
        "Basketball Socks",
        7,
        "US$16",
        ProductCategory.SOCKS,
        isOnSale = true
    ),
    ProductItem(
        6,
        R.drawable.img_socks_3,
        "Nike Multiplier",
        "Running Ankle Socks (2 Pairs)",
        3,
        "US$14",
        ProductCategory.SOCKS
    ),
    ProductItem(
        7,
        R.drawable.img_socks_4,
        "Nike Spark",
        "Lightweight No-Show Socks",
        4,
        "US$12",
        ProductCategory.SOCKS
    ),

    ProductItem(
        8,
        R.drawable.img_shoes_1,
        "Nike Air Force 1 '07",
        "Women's Shoes",
        5,
        "US$115",
        ProductCategory.SHOES,
        isBestSeller = true
    ),
    ProductItem(
        9,
        R.drawable.img_shoes_2,
        "Air Jordan XXXVI",
        "Men's Shoes",
        2,
        "US$185",
        ProductCategory.SHOES,
        isBestSeller = true
    ),
    ProductItem(
        10,
        R.drawable.img_shoes_3,
        "Nike Dunk Low",
        "Men's Shoes",
        6,
        "US$110",
        ProductCategory.SHOES,
        isOnSale = true
    ),
    ProductItem(
        11,
        R.drawable.img_shoes_4,
        "Nike Air Max 90",
        "Women's Shoes",
        4,
        "US$130",
        ProductCategory.SHOES
    ),
    ProductItem(
        12,
        R.drawable.img_shoes_5,
        "Nike Blazer Mid '77",
        "Men's Shoes",
        3,
        "US$100",
        ProductCategory.SHOES,
        isBestSeller = true,
        isOnSale = true
    ),
    ProductItem(
        13,
        R.drawable.img_shoes_6,
        "Nike React Infinity",
        "Running Shoes",
        5,
        "US$160",
        ProductCategory.SHOES
    ),
    ProductItem(
        14,
        R.drawable.img_shoes_7,
        "Nike Pegasus 40",
        "Men's Road Shoes",
        4,
        "US$130",
        ProductCategory.SHOES
    ),
    ProductItem(
        15,
        R.drawable.img_shoes_8,
        "Nike Free RN",
        "Women's Running Shoes",
        3,
        "US$100",
        ProductCategory.SHOES
    ),
    ProductItem(
        16,
        R.drawable.img_shoes_9,
        "Nike Court Vision",
        "Men's Shoes",
        2,
        "US$80",
        ProductCategory.SHOES
    ),
)

@Preview(showBackground = true)
@Composable
private fun PurchaseScreenPreview() {
    NikeTheme {
        PurchaseScreen()
    }
}