package com.example.nike.presentation.main.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.nike.R
import com.example.nike.core.navigation.MainTabRoute
import com.example.nike.core.navigation.Route
import com.example.nike.presentation.cart.navigation.Cart
import com.example.nike.presentation.home.navigation.Home
import com.example.nike.presentation.profile.navigation.Profile
import com.example.nike.presentation.purchase.navigation.Purchase
import com.example.nike.presentation.wishlist.navigation.Wishlist

enum class MainTab(
    @param:DrawableRes val icon: Int,
    @param:StringRes val label: Int,
    val route: MainTabRoute
) {
    HOME(
        icon = R.drawable.ic_nav_home,
        label = R.string.nav_home,
        route = Home,
    ),
    PURCHASE(
        icon = R.drawable.ic_nav_purchase,
        label = R.string.nav_purchase,
        route = Purchase,
    ),
    WISHLIST(
        icon = R.drawable.ic_nav_wishlist,
        label = R.string.nav_wishlist,
        route = Wishlist,
    ),
    CART(
        icon = R.drawable.ic_nav_cart,
        label = R.string.nav_cart,
        route = Cart,
    ),
    PROFILE(
        icon = R.drawable.ic_nav_profile,
        label = R.string.nav_profile,
        route = Profile,
    );

    companion object {
        fun find(predicate: (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        fun contains(predicate: (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}