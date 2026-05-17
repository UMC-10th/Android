package com.example.NikeApp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.NikeApp.R
import kotlinx.serialization.Serializable

/**
 * 앱 내에서 이동할 수 있는 모든 화면
 * @Serializable + sealed interface 구조
 */
sealed interface AppDestination {
    @Serializable
    data object Home : AppDestination

    @Serializable
    data object Purchase : AppDestination

    @Serializable
    data object Wishlist : AppDestination

    @Serializable
    data object Cart : AppDestination

    @Serializable
    data object Profile : AppDestination
}

/**
 * 하단네비게이션에 노출할 탭 메뉴
 */
enum class BottomTab(
    val route: AppDestination,
    @DrawableRes val iconRes: Int,
) {
    Home(AppDestination.Home, R.drawable.home_menu),
    Purchase(AppDestination.Purchase, R.drawable.shop_menu),
    Wishlist(AppDestination.Wishlist, R.drawable.wishlist_menu),
    Cart(AppDestination.Cart, R.drawable.cart_menu),
    Profile(AppDestination.Profile, R.drawable.profile_menu),
}

/** 현재 보여지는 NavDestination이 어떤 탭에 해당하는지 매칭. */
fun NavDestination?.toBottomTab(): BottomTab? =
    BottomTab.entries.firstOrNull { tab ->
        this?.hasRoute(tab.route::class) == true
    }
