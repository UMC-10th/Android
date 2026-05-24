package com.example.nike.presentation.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewestItem (
    @DrawableRes imageRes: Int,
    name: String,
    price: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(314.dp),
    ) {
        Image (
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(314.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = name,
            color = Color.Black,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = price,
            color = Color(0xFF767676),
            fontSize = 14.sp
        )
    }
}