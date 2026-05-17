package com.example.nike.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nike.presentation.main.component.MainBottomBar
import com.example.nike.presentation.main.component.MainTab

@Composable
fun MainScreen(
    appState: MainAppState = rememberMainAppState(),
) {
    val currentTab by appState.currentTab.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            MainBottomBar(
                items = MainTab.entries,
                selectedItem = currentTab,
                onItemSelected = appState::navigate,
                modifier = Modifier.navigationBarsPadding()
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        MainNavHost(
            appState = appState,
            innerPadding = innerPadding
        )
    }
}