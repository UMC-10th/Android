package com.clone.nike_compose.presentation.wish

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.nike_compose.R
import com.clone.nike_compose.core.data.Goods
import com.clone.nike_compose.presentation.purchase.PurchaseGoodsItem

@Preview(showBackground = true)
@Composable
fun PreviewWishScreen() {
    MaterialTheme {
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
            )
        )

        WishScreen(sampleGoods)
    }
}

@Composable
fun WishScreen(wishGoodsList: List<Goods>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 44.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Text(
            text = "위시리스트",
            fontSize = 28.sp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        WishGridList(wishGoodsList)
    }
}

@Composable
fun WishGridList(
    wishGoodsList: List<Goods>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(7.dp)
    ) {
        items(wishGoodsList) { goods ->
            WishGoodsItem(goods)
        }
    }
}

@Composable
fun WishGoodsItem(goods: Goods) {
    Column() {
        Box {
            Image(
                painter = painterResource(goods.goodsImgResId),
                contentDescription = goods.goodsName,
                modifier = Modifier.size(184.dp)
            )
            Image(
                painter = painterResource(R.drawable.icon_wish_on),
                contentDescription = "위시 버튼",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(34.dp)
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