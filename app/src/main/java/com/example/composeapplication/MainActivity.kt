package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composeapplication.ui.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 프로젝트 테마가 있다면 감싸주셔도 좋습니다. (예: ComposeApplicationTheme)
            MainScreen()
        }
    }
}