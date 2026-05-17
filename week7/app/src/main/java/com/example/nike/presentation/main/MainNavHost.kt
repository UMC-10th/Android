package com.example.nike.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.nike.presentation.cart.navigation.cartGraph
import com.example.nike.presentation.home.navigation.homeGraph
import com.example.nike.presentation.profile.navigation.profileGraph
import com.example.nike.presentation.purchase.navigation.purchaseGraph
import com.example.nike.presentation.wishlist.navigation.wishlistGraph

@Composable
fun MainNavHost(
    appState: MainAppState,
    innerPadding: PaddingValues,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = appState.startDestination,
        modifier = Modifier,
        enterTransition = {
            fadeIn(
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(500)
            )
        },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        homeGraph(
            navController = navController,
            innerPadding = innerPadding
        )

        purchaseGraph(
            navController = navController,
            innerPadding = innerPadding
        )

        wishlistGraph(
            navController = navController,
            innerPadding = innerPadding
        )

        cartGraph(
            navController = navController,
            innerPadding = innerPadding
        )

        profileGraph(
            navController = navController,
            innerPadding = innerPadding
        )
    }
}