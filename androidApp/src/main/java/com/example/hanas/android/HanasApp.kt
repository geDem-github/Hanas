package com.example.hanas.android

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hanas.android.ui.screen.home.ChatScreen
import com.example.hanas.android.ui.screen.home.HomeScreen
import com.example.hanas.android.ui.theme.HanasTheme
import kotlinx.serialization.Serializable

@Serializable
sealed interface HanasScreen {
    @Serializable
    data object Home : HanasScreen

    @Serializable
    data object Chat : HanasScreen
}

@Composable
fun HanasApp() {
    val navController = rememberNavController()

    MaterialTheme {
        HanasTheme {
            NavHost(
                modifier = Modifier,
                navController = navController,
                startDestination = HanasScreen.Home,
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
                composable<HanasScreen.Home> {
                    HomeScreen(
                        onClickChatNavCard = {
                            navController.navigate(HanasScreen.Chat) {
                                popUpTo(checkNotNull(navController.graph.findStartDestination().route)) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }

                composable<HanasScreen.Chat> {
                    ChatScreen()
                }
            }
        }
    }
}
