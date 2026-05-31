package com.clone.nike_compose.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.nike_compose.R
import com.clone.nike_compose.core.data.Goods

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
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

        HomeScreen(sampleGoods)
    }
}

@Composable
fun HomeScreen(
    newGoodsList: List<Goods>
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        item {
            HomeHeader()
        }

        item {
            HomeBanner()
        }

        item {
            HomeGoodsRow(newGoodsList)
        }
    }
}

@Composable
fun HomeHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 24.dp)
    ) {
        Text(
            text = "Discover",
            fontSize = 28.sp
        )

        Text(
            text = "9월 4일 목요일",
            fontSize = 16.sp
        )
    }
}

@Composable
fun HomeBanner() {
    Image(
        painter = painterResource(id = R.drawable.image_home_banner),
        contentDescription = "홈 배너",
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun HomeGoodsRow(
    newGoodsList: List<Goods>
) {
    Text(
        text = "What's new?",
        fontSize = 16.sp
    )

    Text(
        text = "나이키 최신 상품",
        fontSize = 28.sp
    )


    LazyRow(
        modifier = Modifier.padding(top = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(newGoodsList) { goods ->
            HomeGoodsItem(goods)
        }
    }
}


@Composable
fun HomeGoodsItem(
    goods: Goods
) {
    Column(
        modifier = Modifier.width(314.dp)
    ) {
        Image(
            painter = painterResource(goods.goodsImgResId),
            contentDescription = goods.goodsName,
            modifier = Modifier
                .fillMaxWidth()
                .height(314.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = goods.goodsName,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = goods.goodsPrice,
            fontSize = 18.sp
        )
    }
}