package com.example.nike.presentation.wishlist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nike.R
import com.example.nike.presentation.wishlist.model.WishlistItem

@Composable
fun WishlistItemCard(
    item: WishlistItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(item.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(14.dp))

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = item.name,
                color = Color.Black,
                fontSize = 14.sp
            )

            Text(
                text = item.subName,
                color = Color(0xFF767676),
                fontSize = 14.sp
            )

            Text(
                text = stringResource(R.string.wishlist_item_colours, item.colours),
                color = Color(0xFF767676),
                fontSize = 14.sp
            )

            Text(
                text = item.price,
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }
}