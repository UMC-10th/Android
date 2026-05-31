package com.example.nikecompose.feature.wishlist.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nikecompose.domain.model.ProductItem

@Composable
fun WishlistProductCard(
    product: ProductItem
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color(0xFFF5F5F5)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            lineHeight = 14.sp
        )

        Text(
            text = product.price,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        if (product.id == 2) {
            Text(
                text = product.subTitle,
                fontSize = 10.sp,
                color = Color.Gray,
                lineHeight = 13.sp
            )

            Text(
                text = product.colors,
                fontSize = 10.sp,
                color = Color.Gray,
                lineHeight = 13.sp
            )

            Text(
                text = "US$10",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}