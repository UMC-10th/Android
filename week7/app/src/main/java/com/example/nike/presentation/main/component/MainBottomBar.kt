package com.example.nike.presentation.main.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nike.core.extension.noRippleClickable

@Composable
fun MainBottomBar(
    items: List<MainTab>,
    selectedItem: MainTab?,
    onItemSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        items.forEach { item ->
            MainBottomBarItem(
                icon = item.icon,
                label = item.label,
                modifier = Modifier.weight(1f)
                    .noRippleClickable(
                        onClick = { onItemSelected(item) }
                    ),
                isSelected = selectedItem == item
            )
        }
    }
}

@Composable
private fun MainBottomBarItem(
    @DrawableRes icon: Int,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    val color = if (isSelected) Color.Black else Color(0xFF767676)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            tint = color,
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = stringResource(label),
            color = Color(0xFF767676),
            fontSize = 10.sp
        )
    }
}