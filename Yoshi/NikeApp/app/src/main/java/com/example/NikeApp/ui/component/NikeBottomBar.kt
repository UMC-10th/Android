package com.example.NikeApp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.NikeApp.ui.navigation.BottomTab
import com.example.NikeApp.ui.theme.NikeBlack
import com.example.NikeApp.ui.theme.NikeGray
import com.example.NikeApp.ui.theme.NikeWhite

/**
하단 네비게이션 바
 */
@Composable
fun NikeBottomBar(
    selectedTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(NikeWhite)
            // 시스템 네비게이션 바와 겹치지 않도록 함
            .windowInsetsPadding(WindowInsets.navigationBars)
            .height(64.dp)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        BottomTab.entries.forEach { tab ->
            BottomBarItem(
                tab = tab,
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    tab: BottomTab,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val tint: Color = if (selected) NikeBlack else NikeGray
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = tab.iconRes),
            contentDescription = tab.name,
            tint = tint,
            modifier = Modifier.height(44.dp),
        )
    }
}
