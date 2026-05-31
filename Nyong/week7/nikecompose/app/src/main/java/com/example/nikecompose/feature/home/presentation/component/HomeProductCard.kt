package com.example.nikecompose.feature.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
fun HomeProductCard(
    product: ProductItem
) {
    Column(
        modifier = Modifier.width(230.dp)
    ) {
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(Color(0xFFF5F5F5)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = product.name,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            lineHeight = 14.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = product.price,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}