package com.example.nike.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.nike.core.extension.clearBackStackNavOptions
import com.example.nike.core.navigation.MainTabRoute
import com.example.nike.presentation.home.HomeRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToHome(
    navOptions: NavOptions? = clearBackStackNavOptions(),
) {
    navigate(
        route = Home,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    innerPadding: PaddingValues
) {
    composable<Home> {
        HomeRoute (
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Serializable
data object Home : MainTabRoute