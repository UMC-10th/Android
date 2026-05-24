package com.example.nikecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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

data class ProductItem(
    val id: Int,
    @DrawableRes val imageResId: Int,
    val name: String,
    val subTitle: String,
    val colors: String,
    val price: String,
    val isLiked: Boolean = false,
    val badge: String? = null
)

val productList = listOf(
    ProductItem(
        id = 1,
        imageResId = R.drawable.img_shoe_1,
        name = "Air Jordan XXXVI",
        subTitle = "New Release",
        colors = "1 Colour",
        price = "US$185",
        isLiked = false
    ),
    ProductItem(
        id = 2,
        imageResId = R.drawable.img_shoe_2,
        name = "Nike Air Force 1 '07",
        subTitle = "Women's Shoes",
        colors = "5 Colours",
        price = "US$115",
        isLiked = true,
        badge = "BestSeller"
    )
)

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
        containerColor = Color.White,
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

@Composable
fun NikeBottomBar(
    items: List<BottomRoute>,
    currentRoute: String?,
    onItemClick: (BottomRoute) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            val iconColor = if (selected) Color.Black else Color.Gray

            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = item.label,
                        modifier = Modifier.size(22.dp),
                        tint = iconColor
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 10.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 32.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Discover",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "9월 4일 목요일",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(28.dp))

            Image(
                painter = painterResource(id = R.drawable.img_home_logo),
                contentDescription = "홈 배너 이미지",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "What's new",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "나이키 최신 상품",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = productList,
                key = { product -> product.id }
            ) { product ->
                HomeProductCard(product = product)
            }
        }
    }
}

@Composable
fun HomeProductCard(
    product: ProductItem
) {
    Column(
        modifier = Modifier.width(180.dp)
    ) {
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color(0xFFF5F5F5)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = product.name,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = product.price,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun ShopScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("전체", "Tops & T-shirts", "sale")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            contentColor = Color.Black
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 12.sp
                        )
                    }
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            items(
                items = productList,
                key = { product -> product.id }
            ) { product ->
                ShopProductCard(product = product)
            }
        }
    }
}

@Composable
fun ShopProductCard(
    product: ProductItem
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color(0xFFF5F5F5)),
                contentScale = ContentScale.Crop
            )

            Icon(
                painter = painterResource(
                    id = if (product.isLiked) R.drawable.ic_heart_on else R.drawable.ic_heart_off
                ),
                contentDescription = "좋아요",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(22.dp),
                tint = Color.Unspecified
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        product.badge?.let {
            Text(
                text = it,
                fontSize = 11.sp,
                color = Color(0xFFFF6A00),
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = product.name,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            lineHeight = 14.sp
        )

        Text(
            text = product.subTitle,
            fontSize = 10.sp,
            color = Color.Gray,
            lineHeight = 13.sp
        )

        Text(
            text = product.colors,
            fontSize = 10.sp,
            color = Color.Gray,
            lineHeight = 13.sp
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = product.price,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun WishlistScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "위시리스트",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 24.dp, top = 40.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 28.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            items(
                items = productList,
                key = { product -> product.id }
            ) { product ->
                WishlistProductCard(product = product)
            }
        }
    }
}

@Composable
fun WishlistProductCard(
    product: ProductItem
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color(0xFFF5F5F5)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            lineHeight = 14.sp
        )

        Text(
            text = product.price,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        if (product.id == 2) {
            Text(
                text = product.subTitle,
                fontSize = 10.sp,
                color = Color.Gray,
                lineHeight = 13.sp
            )

            Text(
                text = product.colors,
                fontSize = 10.sp,
                color = Color.Gray,
                lineHeight = 13.sp
            )

            Text(
                text = "US$10",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun CartScreen(
    onOrderClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_cartcircle),
                    contentDescription = "장바구니 아이콘",
                    modifier = Modifier.size(56.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "장바구니가 비어 있습니다.\n제품을 추가하면 여기에 표시됩니다.",
                    fontSize = 12.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )
            }
        }

        Button(
            onClick = onOrderClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "주문하기",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Text(
            text = "프로필",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}