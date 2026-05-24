package com.example.nike.presentation.purchase.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nike.R
import com.example.nike.presentation.purchase.ProductItem

@Composable
fun PurchaseItemCard(
    item: ProductItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Box (
            modifier = Modifier,
        ) {
            Image (
                painter = painterResource(item.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )

            Icon (
                imageVector = ImageVector.vectorResource(R.drawable.ic_product_like_empty),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Column (
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            if (item.isBestSeller) {
                Text (
                    text = stringResource(R.string.purchase_item_best_seller),
                    color = Color(0xFFFC5100),
                    fontSize = 14.sp,
                )
            }

            Text (
                text = item.name,
                color = Color.Black,
                fontSize = 14.sp
            )

            Text (
                text = item.subName,
                color = Color(0xFF767676),
                fontSize = 14.sp
            )

            Text (
                text = stringResource(R.string.purchase_item_colours, item.colours),
                color = Color(0xFF767676),
                fontSize = 14.sp
            )

            Text (
                text = item.price,
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }
}