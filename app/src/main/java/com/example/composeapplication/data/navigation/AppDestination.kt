package com.example.composeapplication.data.navigation

import com.example.composeapplication.R
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppDestination {
    val title: String
    // TODO: 프로젝트에 맞는 아이콘 R.drawable ID를 넣으세요!
    val iconId: Int

    @Serializable
    data object Home : AppDestination {
        override val title = "홈"
        override val iconId = R.drawable.ic_home
    }

    @Serializable
    data object Shop : AppDestination {
        override val title = "구매하기"
        override val iconId = R.drawable.ic_shop
    }

    @Serializable
    data object Wishlist : AppDestination {
        override val title = "위시리스트"
        override val iconId = R.drawable.ic_wishlist
    }

    @Serializable
    data object Cart : AppDestination {
        override val title = "장바구니"
        override val iconId = R.drawable.ic_cart
    }

    @Serializable
    data object Profile : AppDestination {
        override val title = "프로필"
        override val iconId = R.drawable.ic_profile
    }
}