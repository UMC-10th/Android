package com.example.NikeApp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.NikeApp.ui.theme.NikeAppTheme
import com.example.NikeApp.ui.theme.NikeBlack
import com.example.NikeApp.ui.theme.NikeGray
import com.example.NikeApp.ui.theme.NikeLightGray

/**
 * 구매하기 화면.
 */
private enum class PurchaseTab(val label: String) {
    All("전체"),
    Tops("Tops & T-shirts"),
    Shoes("Shoes"),
}

@Composable
fun PurchaseScreen(modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableStateOf(PurchaseTab.All) }

    Column(modifier = modifier.fillMaxSize()) {
        PurchaseTabBar(
            selected = selectedTab,
            onTabSelected = { selectedTab = it },
        )

        // 본문은 빈 화면
        Box(modifier = Modifier.fillMaxSize())
    }
}

@Composable
private fun PurchaseTabBar(
    selected: PurchaseTab,
    onTabSelected: (PurchaseTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
    ) {
        PurchaseTab.entries.forEach { tab ->
            PurchaseTabItem(
                label = tab.label,
                selected = tab == selected,
                onClick = { onTabSelected(tab) },
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun PurchaseTabItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = label,
                fontSize = 15.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                color = if (selected) NikeBlack else NikeGray,
                textAlign = TextAlign.Center,
            )
        }
        // 선택된 탭 UI 전환
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (selected) 2.dp else 1.dp)
                .background(if (selected) NikeBlack else NikeLightGray),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PurchaseScreenPreview() {
    NikeAppTheme { PurchaseScreen() }
}
