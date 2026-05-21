package com.example.nike.presentation.purchase.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.nike.core.extension.clearBackStackNavOptions
import com.example.nike.core.navigation.MainTabRoute
import com.example.nike.presentation.cart.CartRoute
import com.example.nike.presentation.purchase.PurchaseRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToPurchase(
    navOptions: NavOptions? = clearBackStackNavOptions(),
) {
    navigate(
        route = Purchase,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.purchaseGraph(
    navController: NavController,
    innerPadding: PaddingValues
) {
    composable<Purchase> {
        PurchaseRoute (
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Serializable
data object Purchase : MainTabRoute