package com.example.hanas.android.ui.feature.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hanas.android.ui.feature.chat.ChatScreen
import kotlinx.serialization.Serializable

@Serializable
object ChatScreenDestination

fun NavGraphBuilder.chatScreen(navController: NavController) {
    composable<ChatScreenDestination> {
        ChatScreen(navController = navController)
    }
}
