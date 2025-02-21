package com.example.hanas.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.hanas.android.ui.screen.home.HomeScreen
import com.example.hanas.android.ui.theme.HanasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HanasTheme {
                HomeScreen()
            }
        }
    }
}
