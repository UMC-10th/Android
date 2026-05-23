package com.example.NikeApp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.NikeApp.R
import com.example.NikeApp.model.Product
import com.example.NikeApp.model.formattedPrice
import com.example.NikeApp.ui.theme.NikeBlack
import com.example.NikeApp.ui.theme.NikeGray
import com.example.NikeApp.ui.theme.NikeLightGray

// 하트 ON 색상
private val HeartOnColor = Color(0xFFE61E2B)

/**
 * 홈/구매하기/위시리스트 화면에서 공통으로 사용하는 상품 카드
 *  [showWishButton]이 true이면 우상단에 하트 버튼 노출
 */
@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    showWishButton: Boolean = false,
    isWished: Boolean = false,
    onWishClick: (() -> Unit)? = null,
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(NikeLightGray),
            contentAlignment = Alignment.Center,
        ) {
            // 실제 사진이 등록된 상품은 실사진으로 아니면 작은 더미 아이콘으로 표시됨
            if (product.imageRes != null) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = product.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(80.dp),
                )
            }

            if (showWishButton) {
                WishHeartButton(
                    isWished = isWished,
                    onClick = { onWishClick?.invoke() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = product.name,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = NikeBlack,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = product.formattedPrice(),
            fontSize = 13.sp,
            color = NikeGray,
        )
    }
}

/**
 * 상품 카드 우상단의 하트 토글 버튼.
 */
@Composable
private fun WishHeartButton(
    isWished: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(RoundedCornerShape(50))
            .background(Color.White.copy(alpha = 0.85f))
            .clickable(onClick = onClick)
            .padding(PaddingValues(6.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(
                id = if (isWished) R.drawable.ic_heart_on else R.drawable.ic_heart_off,
            ),
            contentDescription = if (isWished) "위시리스트에서 제거" else "위시리스트에 추가",
            tint = if (isWished) HeartOnColor else NikeGray,
        )
    }
}
