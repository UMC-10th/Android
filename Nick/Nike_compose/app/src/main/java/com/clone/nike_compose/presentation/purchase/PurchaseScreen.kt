package com.clone.nike_compose.presentation.purchase

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clone.nike_compose.R
import com.clone.nike_compose.core.data.Goods

@Preview(showBackground = true)
@Composable
fun PreviewPurchaseScreen() {
    MaterialTheme {
        PurchaseScreen(goodsList = sampleGoods)
    }
}

@Composable
fun PurchaseScreen(goodsList: List<Goods>) {
    PurchaseGridList(goodsList)
}

@Composable
fun PurchaseGridList(goodsList: List<Goods>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(7.dp)
    ) {
        items(goodsList) { goods ->
            PurchaseGoodsItem(goods)
        }
    }
}

@Composable
fun PurchaseGoodsItem(goods: Goods) {
    Column {
        Image(
            painter = painterResource(goods.goodsImgResId),
            contentDescription = goods.goodsName,
            modifier = Modifier.size(184.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = goods.goodsName
            )

            Text(
                text = goods.category
            )

            Text(
                text = goods.numberOfColour
            )

            Text(
                text = goods.goodsPrice
            )
        }
    }
}

val sampleGoods = listOf(
    Goods(
        goodsId = 1,
        goodsName = "Nike Air Max",
        goodsPrice = "199,000원",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_home_banner
    ),
    Goods(
        goodsId = 2,
        goodsName = "Nike Jordan",
        goodsPrice = "239,000원",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_home_banner
    ),
    Goods(
        goodsId = 3,
        goodsName = "Nike Jordan",
        goodsPrice = "239,000원",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_home_banner
    ),
    Goods(
        goodsId = 4,
        goodsName = "Nike Jordan",
        goodsPrice = "239,000원",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_home_banner
    ),
    Goods(
        goodsId = 5,
        goodsName = "Nike Jordan",
        goodsPrice = "239,000원",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_home_banner
    ),
    Goods(
        goodsId = 6,
        goodsName = "Nike Jordan",
        goodsPrice = "239,000원",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_home_banner
    ),
    Goods(
        goodsId = 7,
        goodsName = "Nike Jordan",
        goodsPrice = "239,000원",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_home_banner
    ),
    Goods(
        goodsId = 8,
        goodsName = "Nike Jordan",
        goodsPrice = "239,000원",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_home_banner
    )
)