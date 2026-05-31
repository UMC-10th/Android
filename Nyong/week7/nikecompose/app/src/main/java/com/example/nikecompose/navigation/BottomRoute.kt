package com.example.nikecompose.navigation

import androidx.annotation.DrawableRes
import com.example.nikecompose.R

sealed class BottomRoute(
    val route: String,
    val label: String,
    @DrawableRes val iconResId: Int
) {
    data object Home : BottomRoute("home", "홈", R.drawable.ic_home)
    data object Shop : BottomRoute("shop", "구매하기", R.drawable.ic_buy)
    data object Wishlist : BottomRoute("wishlist", "위시리스트", R.drawable.ic_wishlist)
    data object Cart : BottomRoute("cart", "장바구니", R.drawable.ic_cart)
    data object Profile : BottomRoute("profile", "프로필", R.drawable.ic_profile)
}