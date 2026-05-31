package com.example.nikecompose.feature.shop.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nikecompose.R
import com.example.nikecompose.domain.model.ProductItem

@Composable
fun ShopProductCard(
    product: ProductItem
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
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

            Icon(
                painter = painterResource(
                    id = if (product.isLiked) R.drawable.ic_heart_on else R.drawable.ic_heart_off
                ),
                contentDescription = "좋아요",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(22.dp),
                tint = Color.Unspecified
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        product.badge?.let {
            Text(
                text = it,
                fontSize = 11.sp,
                color = Color(0xFFFF6A00),
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = product.name,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            lineHeight = 14.sp
        )

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

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = product.price,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}