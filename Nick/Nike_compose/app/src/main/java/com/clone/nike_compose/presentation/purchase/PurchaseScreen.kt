package com.clone.nike_compose.presentation.purchase

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var purchaseGoodsList by remember { mutableStateOf(goodsList) }

    PurchaseGridList(
        goodsList = purchaseGoodsList,
        wishOnClick = { goodsId ->

            purchaseGoodsList =
                purchaseGoodsList.map { goods ->

                    if (goods.goodsId == goodsId) {

                        goods.copy(
                            isWished = !goods.isWished
                        )

                    } else {
                        goods
                    }
                }
        }
    )
}

@Composable
fun PurchaseGridList(
    goodsList: List<Goods>,
    wishOnClick: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(7.dp)
    ) {
        items(goodsList) { goods ->
            PurchaseGoodsItem(
                goods,
                wishOnClick
            )
        }
    }
}

@Composable
fun PurchaseGoodsItem(
    goods: Goods,
    wishOnClick: (Int) -> Unit) {
    Column {
        Box {
            Image(
                painter = painterResource(goods.goodsImgResId),
                contentDescription = goods.goodsName,
                modifier = Modifier.size(184.dp)
            )
            Image(
                painter = painterResource(
                    if(goods.isWished) {
                        R.drawable.icon_wish_on
                    } else {
                        R.drawable.icon_wish_off
                    }),
                contentDescription = "위시 버튼",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(34.dp)
                    .clickable {
                        wishOnClick(goods.goodsId)
                    }
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(horizontal = 14.dp)
        ) {
            Text(
                text = goods.goodsName,
                fontSize = 14.sp
            )

            Text(
                text = goods.category,
                fontSize = 14.sp
            )

            Text(
                text = goods.numberOfColour,
                fontSize = 14.sp
            )

            Text(
                text = goods.goodsPrice,
                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(41.dp)
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