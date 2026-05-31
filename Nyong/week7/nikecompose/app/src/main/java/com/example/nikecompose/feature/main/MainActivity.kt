package com.example.nikecompose.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nikecompose.core.component.NikeBottomBar
import com.example.nikecompose.feature.cart.presentation.CartScreen
import com.example.nikecompose.feature.home.presentation.HomeScreen
import com.example.nikecompose.feature.profile.presentation.ProfileScreen
import com.example.nikecompose.feature.shop.presentation.ShopScreen
import com.example.nikecompose.feature.wishlist.presentation.WishlistScreen
import com.example.nikecompose.navigation.BottomRoute
import com.example.nikecompose.ui.theme.NikeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NikeComposeTheme {
                NikeComposeApp()
            }
        }
    }
}

@Composable
fun NikeComposeApp() {
    val navController = rememberNavController()

    val bottomItems = listOf(
        BottomRoute.Home,
        BottomRoute.Shop,
        BottomRoute.Wishlist,
        BottomRoute.Cart,
        BottomRoute.Profile
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NikeBottomBar(
                items = bottomItems,
                currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route,
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomRoute.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(BottomRoute.Home.route) {
                HomeScreen()
            }

            composable(BottomRoute.Shop.route) {
                ShopScreen()
            }

            composable(BottomRoute.Wishlist.route) {
                WishlistScreen()
            }

            composable(BottomRoute.Cart.route) {
                CartScreen(
                    onOrderClick = {
                        navController.navigate(BottomRoute.Shop.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

            composable(BottomRoute.Profile.route) {
                ProfileScreen()
            }
        }
    }
}