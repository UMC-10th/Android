package com.example.nike.presentation.cart.navigation

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
import com.example.nike.presentation.purchase.navigation.Purchase
import com.example.nike.presentation.purchase.navigation.navigateToPurchase
import kotlinx.serialization.Serializable

fun NavController.navigateToCart(
    navOptions: NavOptions? = clearBackStackNavOptions(),
) {
    navigate(
        route = Cart,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.cartGraph(
    navController: NavController,
    innerPadding: PaddingValues
) {
    composable<Cart> {
        CartRoute (
            modifier = Modifier.padding(innerPadding),
            onOrderClick = { navController.navigateToPurchase() }
        )
    }
}

@Serializable
data object Cart : MainTabRoute