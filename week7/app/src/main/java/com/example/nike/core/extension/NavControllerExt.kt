package com.example.nike.core.extension

import androidx.navigation.NavController
import androidx.navigation.navOptions

fun NavController.clearBackStackNavOptions() = navOptions {
    popUpTo(0) {
        inclusive = true
    }
    launchSingleTop = true
}

fun NavController.clearBackStackWithRestoreNavOptions() = navOptions {
    popUpTo(0) {
        saveState = true
        inclusive = true
    }
    restoreState = true
}