package com.example.composeapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.R

@Composable
fun CartScreen(
    // ⭐️ 장바구니에서 '주문하기'를 눌렀을 때 실행할 행동을 부모에게 위임합니다.
    onNavigateToShop: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        // 화면 중앙: 장바구니가 비어 있다는 안내 문구
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bagcircle),
                contentDescription = "Empty Cart",
                modifier = Modifier
                    .size(100.dp) // 너비와 높이를 모두 100dp로 고정
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "장바구니가 비어 있습니다",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "제품을 추가하면 여기에 표시됩니다.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // 화면 최하단: 주문하기 버튼 (나이키 스타일로 검은색 배치)
        Button(
            onClick = { onNavigateToShop() }, // ⭐️ 클릭 시 부모에게 인터폰을 울림!
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black, // 나이키 시그니처 블랙
                contentColor = Color.White
            )
        ) {
            Text(
                text = "주문하기",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}