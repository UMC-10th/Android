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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.clone.nike_compose.R
import com.clone.nike_compose.presentation.ui.cart.CartScreen
import com.clone.nike_compose.presentation.ui.home.HomeScreen
import com.clone.nike_compose.presentation.ui.profile.ProfileScreen
import com.clone.nike_compose.presentation.ui.purchase.PurchaseScreen
import com.clone.nike_compose.presentation.ui.theme.Nike_composeTheme
import com.clone.nike_compose.presentation.ui.wish.WishScreen
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
                HomeScreen()
            }
            composable<AppDestination.Cart> {
                CartScreen(moveToPurchase = { navController.navigate(AppDestination.Purchase)})
            }
            composable<AppDestination.Wish> {
                WishScreen()
            }
            composable<AppDestination.Profile> {
                ProfileScreen()
            }
            composable<AppDestination.Purchase> {
                PurchaseScreen()
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

