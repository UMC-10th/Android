package com.example.composeapplication.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapplication.data.AppDestination

@Composable
fun MainScreen() {
    // 1. 내비게이션 조종사(navController) 임명
    val navController = rememberNavController()

    // 2. 현재 백스택 상태를 실시간으로 관찰하여 어떤 탭이 활성화되었는지 파악
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // 하단 탭 바에 나열할 5개 화면 리스트
    val items = listOf(
        AppDestination.Home,
        AppDestination.Shop,
        AppDestination.Wishlist,
        AppDestination.Cart,
        AppDestination.Profile
    )

    Scaffold(
        bottomBar = {
            // [ ] 메인 Activity에 BottomBar를 추가하여 하단 탭 내비게이션 구현하기 완료
            NavigationBar {
                items.forEach { screen ->
                    // 현재 띄워진 화면의 클래스 타입이 이 버튼의 클래스 타입과 일치하는지 체크 (Type-Safe)
                    val isSelected = currentDestination?.hasRoute(screen::class) == true

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            // 탭 클릭 시 해당 화면으로 이동
                            navController.navigate(screen) {
                                // 뒤로가기를 누르면 무조건 startDestination(홈)으로 오도록 스택 정리
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true // 같은 화면 연속 중복 생성 방지
                                restoreState = true    // 이전 탭 상태 복구
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.iconId),
                                contentDescription = screen.title
                            )
                        },
                        label = { Text(screen.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        // innerPadding을 NavHost에 반드시 꽂아야 하단바가 화면 레이아웃을 가리지 않습니다.
        NavHost(
            navController = navController,
            startDestination = AppDestination.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            // [ ] 각 탭에 해당하는 Screen을 BottomBar와 연결하기 완료
            composable<AppDestination.Home> { HomeScreen() }
            composable<AppDestination.Shop> { ShopScreen() }
            composable<AppDestination.Wishlist> { WishlistScreen() }

            //  장바구니 -> 구매하기 미션 연동 부분
            composable<AppDestination.Cart> {
                CartScreen(
                    onNavigateToShop = {
                        // [ ] '장바구니' 화면의 '주문하기' 버튼을 클릭하면, '구매하기' 탭으로 전환되도록 구현하기 완료
                        navController.navigate(AppDestination.Shop) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

            composable<AppDestination.Profile> { ProfileScreen() }
        }
    }
}