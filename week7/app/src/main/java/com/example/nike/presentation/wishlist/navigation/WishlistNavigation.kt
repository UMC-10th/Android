package com.example.nike.presentation.wishlist.navigation

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
import com.example.nike.presentation.wishlist.WishlistRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToWishlist(
    navOptions: NavOptions? = clearBackStackNavOptions(),
) {
    navigate(
        route = Wishlist,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.wishlistGraph(
    navController: NavController,
    innerPadding: PaddingValues
) {
    composable<Wishlist> {
        WishlistRoute (
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Serializable
data object Wishlist : MainTabRoute