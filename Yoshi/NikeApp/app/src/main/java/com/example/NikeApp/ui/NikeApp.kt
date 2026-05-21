package com.example.NikeApp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.NikeApp.ui.component.NikeBottomBar
import com.example.NikeApp.ui.navigation.AppDestination
import com.example.NikeApp.ui.navigation.BottomTab
import com.example.NikeApp.ui.navigation.toBottomTab
import com.example.NikeApp.ui.screen.CartScreen
import com.example.NikeApp.ui.screen.HomeScreen
import com.example.NikeApp.ui.screen.ProfileScreen
import com.example.NikeApp.ui.screen.PurchaseScreen
import com.example.NikeApp.ui.screen.WishlistScreen

/**
 * 앱의 최상위 컴포저블
 *  NavController는 화면 이동과 뒤로 가기를 담당
 *  Scaffold는 BottomBar와 본문을 분리하여 배치
 *  NavHost는 실제 화면이 교체되며 그려지는 컨테이너
 */
@Composable
fun NikeApp() {
    val navController = rememberNavController()

    // 현재 어떤 화면이 보여지고 있는지 NavController에서 관찰 → BottomBar 선택 상태로 변환
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentTab: BottomTab = backStackEntry?.destination.toBottomTab() ?: BottomTab.Home

    Scaffold(
        bottomBar = {
            NikeBottomBar(
                selectedTab = currentTab,
                onTabSelected = { tab -> navController.navigateToTab(tab) },
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Home,
            modifier = Modifier.padding(innerPadding),
        ) {
            mainGraph(onNavigateToPurchase = { navController.navigateToTab(BottomTab.Purchase) })
        }
    }
}

/** NavGraph 정의를 확장 함수로 분리 */
private fun NavGraphBuilder.mainGraph(
    onNavigateToPurchase: () -> Unit,
) {
    composable<AppDestination.Home> { HomeScreen() }
    composable<AppDestination.Purchase> { PurchaseScreen() }
    composable<AppDestination.Wishlist> { WishlistScreen() }
    composable<AppDestination.Cart> {
        // 장바구니 → 구매하기
        CartScreen(onOrderClick = onNavigateToPurchase)
    }
    composable<AppDestination.Profile> { ProfileScreen() }
}

/**
 * BottomBar 탭 전환 시 사용하는 navigate 옵션 모음
 */
private fun NavController.navigateToTab(tab: BottomTab) {
    navigate(tab.route) {
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
