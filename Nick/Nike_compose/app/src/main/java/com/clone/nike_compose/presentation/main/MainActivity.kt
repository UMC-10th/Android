package com.clone.nike_compose.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.clone.nike_compose.R
import com.clone.nike_compose.core.data.Goods
import com.clone.nike_compose.presentation.cart.CartScreen
import com.clone.nike_compose.presentation.home.HomeScreen
import com.clone.nike_compose.presentation.profile.ProfileScreen
import com.clone.nike_compose.presentation.purchase.PurchaseScreen
import com.clone.nike_compose.presentation.theme.Nike_composeTheme
import com.clone.nike_compose.presentation.wish.WishScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

val sampleNewGoods = listOf(
    Goods(
        goodsId = 1,
        goodsName = "Jordan XXXVI",
        goodsPrice = "US$185",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_jordan_xxxvi
    ),
    Goods(
        goodsId = 2,
        goodsName = "Air Force 1",
        goodsPrice = "US$115",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_air_force_1
    )
)

val sampleGoods = listOf(
    Goods(
        goodsId = 1,
        goodsName = "Nike Everyday Plus Cushioned",
        goodsPrice = "US$10",
        category = "Training Ankle Socks (6 Pairs)",
        numberOfColour = "5 Colours",
        isWished = false,
        goodsImgResId = R.drawable.image_nike_everyday_plus_cushioned
    ),
    Goods(
        goodsId = 2,
        goodsName = "Jordan ENike Air Force 1 '07ssentials",
        goodsPrice = "US$115",
        category = "Men's shoes",
        numberOfColour = "2 Colours",
        isWished = false,
        goodsImgResId = R.drawable.image_jordan_enike
    ),
    Goods(
        goodsId = 3,
        goodsName = "Jordan XXXVI",
        goodsPrice = "US$185",
        category = "Men's shoes",
        numberOfColour = "1 Colour",
        isWished = false,
        goodsImgResId = R.drawable.image_jordan_xxxvi
    ),
    Goods(
        goodsId = 4,
        goodsName = "Air Force 1",
        goodsPrice = "US$115",
        category = "Men's shoes",
        numberOfColour = "2 Colours",
        isWished = false,
        goodsImgResId = R.drawable.image_air_force_1
    ),
    Goods(
        goodsId = 5,
        goodsName = "Nike Jordan",
        goodsPrice = "239,000원",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_home_banner
    ),
    Goods(
        goodsId = 6,
        goodsName = "Nike Jordan",
        goodsPrice = "239,000원",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_home_banner
    ),
    Goods(
        goodsId = 7,
        goodsName = "Nike Jordan",
        goodsPrice = "239,000원",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_home_banner
    ),
    Goods(
        goodsId = 8,
        goodsName = "Nike Jordan",
        goodsPrice = "239,000원",
        category = "",
        numberOfColour = "",
        isWished = false,
        goodsImgResId = R.drawable.image_home_banner
    )
)

sealed interface AppDestination {
    @Serializable
    data object Home: AppDestination
    @Serializable
    data object Cart: AppDestination
    @Serializable
    data object Profile: AppDestination
    @Serializable
    data object Purchase: AppDestination
    @Serializable
    data object Wish: AppDestination
}

@Preview(showBackground = true) //미리보기 함수를 따로 만드는 경우도 있다고 함
@Composable
fun PreviewMainScreen()
{
    Nike_composeTheme {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    var purchaseGoodsList by remember { mutableStateOf(sampleGoods) }
    var homeNewGoodsList by remember { mutableStateOf(sampleNewGoods) }
    val wishGoodsList = purchaseGoodsList.filter { it.isWished }

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = AppDestination.Home,
            modifier = Modifier.padding(padding)
        ) {
            composable<AppDestination.Home> {
                HomeScreen(newGoodsList = homeNewGoodsList)
            }
            composable<AppDestination.Cart> {
                CartScreen(moveToPurchase = { navController.navigate(AppDestination.Purchase)})
            }
            composable<AppDestination.Wish> {
                WishScreen(wishGoodsList = wishGoodsList)
            }
            composable<AppDestination.Profile> {
                ProfileScreen()
            }
            composable<AppDestination.Purchase> {
                PurchaseScreen(
                    goodsList = purchaseGoodsList,
                    wishOnClick = { goodsId ->
                        purchaseGoodsList = purchaseGoodsList.map { goods ->
                            if (goods.goodsId == goodsId) {
                                goods.copy(isWished = !goods.isWished)
                            } else {
                                goods
                            }
                        }
                    }
                )
            }
        }
    }
}

data class BottomNavItem<T : AppDestination>(
    val route: T,
    val label: String,
    val icon: Int
)

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(
            route = AppDestination.Home,
            label = "Home",
            icon = R.drawable.icon_housesimple
        ),

        BottomNavItem(
            route = AppDestination.Purchase,
            label = "Search",
            icon = R.drawable.icon_listmagnifyingglass

        ),

        BottomNavItem(
            route = AppDestination.Wish,
            label = "Wish",
            icon = R.drawable.icon_heartstraight
        ),

        BottomNavItem(
            route = AppDestination.Profile,
            label = "Profile",
            icon = R.drawable.icon_user
        ),

        BottomNavItem(
            route = AppDestination.Cart,
            label = "Cart",
            icon = R.drawable.icon_bagsimple
        )
    )

    NavigationBar {
        val currentRoute =
            navController.currentBackStackEntryAsState()
                .value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route::class.qualifiedName,

                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = null
                    )
                },

                label = {Text(item.label)}
            )
        }
    }
}

