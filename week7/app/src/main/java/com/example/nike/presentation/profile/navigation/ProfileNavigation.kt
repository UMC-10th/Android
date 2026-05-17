package com.example.nike.presentation.profile.navigation

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
import com.example.nike.presentation.profile.ProfileRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToProfile(
    navOptions: NavOptions? = clearBackStackNavOptions(),
) {
    navigate(
        route = Profile,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.profileGraph(
    navController: NavController,
    innerPadding: PaddingValues
) {
    composable<Profile> {
        ProfileRoute (
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Serializable
data object Profile : MainTabRoute