package com.example.hanas.android

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hanas.android.ui.screen.home.HomeScreen
import com.example.hanas.android.ui.theme.HanasTheme
import kotlinx.serialization.Serializable

sealed interface HanasScreen {
    @Serializable
    data object Home : HanasScreen

    data object Chat : HanasScreen
}

@Composable
fun App() {
    val navController = rememberNavController()

    MaterialTheme {
        HanasTheme {
            NavHost(
                navController = navController,
                startDestination = HanasScreen.Home,
            ) {
                composable<HanasScreen.Home> {
                    HomeScreen()
                }
            }
        }
    }
}
