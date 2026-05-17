package com.example.nike.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.nike.presentation.cart.navigation.navigateToCart
import com.example.nike.presentation.home.navigation.Home
import com.example.nike.presentation.home.navigation.navigateToHome
import com.example.nike.presentation.main.component.MainTab
import com.example.nike.presentation.profile.navigation.navigateToProfile
import com.example.nike.presentation.purchase.navigation.navigateToPurchase
import com.example.nike.presentation.wishlist.navigation.navigateToWishlist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Stable
class MainAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope
) {
    val startDestination = Home

    private val currentDestination = navController.currentBackStackEntryFlow
        .map { it.destination }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val currentTab: StateFlow<MainTab?> = currentDestination
        .map { destination ->
            MainTab.Companion.find { tab ->
                destination?.hasRoute(tab::class) == true
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions = navOptions)
            MainTab.PURCHASE -> navController.navigateToPurchase(navOptions = navOptions)
            MainTab.WISHLIST -> navController.navigateToWishlist(navOptions = navOptions)
            MainTab.CART -> navController.navigateToCart(navOptions = navOptions)
            MainTab.PROFILE -> navController.navigateToProfile(navOptions = navOptions)

        }
    }
}

@Composable
fun rememberMainAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): MainAppState = remember(navController, coroutineScope) {
    MainAppState(navController, coroutineScope)
}