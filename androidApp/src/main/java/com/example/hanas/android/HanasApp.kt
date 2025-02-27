package com.example.hanas.android

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.hanas.android.ui.feature.chat.navigation.chatScreen
import com.example.hanas.android.ui.feature.home.navigation.HomeScreenDestination
import com.example.hanas.android.ui.feature.home.navigation.homeScreen
import com.example.hanas.android.ui.theme.HanasTheme

@Composable
fun HanasApp() {
    val navController = rememberNavController()

    MaterialTheme {
        HanasTheme {
            NavHost(
                modifier = Modifier,
                navController = navController,
                startDestination = HomeScreenDestination,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(300),
                    ) + fadeIn()
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(300),
                    ) + fadeOut()
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(300),
                    ) + fadeIn()
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(300),
                    ) + fadeOut()
                },
            ) {
                homeScreen(navController = navController)

                chatScreen(navController = navController)
            }
        }
    }
}
