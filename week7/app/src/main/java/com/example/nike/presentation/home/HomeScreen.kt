package com.example.nike.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nike.R
import com.example.nike.core.designsystem.theme.NikeTheme
import com.example.nike.presentation.home.component.NewestItemCard
import com.example.nike.presentation.home.model.NewestItem

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
) {
    HomeScreen(
        modifier = modifier,
    )
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                vertical = 50.dp
            ),
    ) {
        item {
            TitleSection()
        }

        item {
            NewestItemCardSection()
        }
    }
}

@Composable
private fun TitleSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = 17.dp,
            ),
    ) {
        Text(
            text = stringResource(R.string.home_title),
            color = Color.Black,
            fontSize = 28.sp,
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.home_date),
            color = Color(0xFF767676),
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(R.drawable.img_home),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun NewestItemCardSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(
                top = 40.dp,
            ),
    ) {
        Text(
            text = stringResource(R.string.home_newest_sub_title),
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(
                horizontal = 42.dp
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.home_newest_title),
            color = Color(0xFF767676),
            fontSize = 28.sp,
            modifier = Modifier.padding(
                horizontal = 42.dp
            )
        )

        Spacer(modifier = Modifier.height(22.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                horizontal = 42.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(
                items = dummyProducts,
                key = { it.id }
            ) { product ->
                NewestItemCard(
                    imageRes = product.imageRes,
                    name = product.name,
                    price = product.price
                )
            }
        }
    }
}

// UI 작업용 더미데이터 선언, 추후 이동
private val dummyProducts = listOf(
    NewestItem(1, R.drawable.img_newest_1, "Air Jordan XXXVI", "US$185"),
    NewestItem(2, R.drawable.img_newest_2, "Nike Dunk Low", "US$110"),
    NewestItem(3, R.drawable.img_newest_3, "Nike Air Max 90", "US$130"),
    NewestItem(4, R.drawable.img_newest_4, "Nike Blazer Mid", "US$100"),
    NewestItem(5, R.drawable.img_newest_5, "Air Force 1 '07", "US$90"),
)

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    NikeTheme {
        HomeScreen()
    }
}