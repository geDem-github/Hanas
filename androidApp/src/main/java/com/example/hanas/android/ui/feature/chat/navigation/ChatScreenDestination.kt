package com.example.hanas.android.ui.feature.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.hanas.android.ui.feature.chat.ChatScreen
import kotlinx.serialization.Serializable

@Serializable
data class ChatScreenDestination(
    val chatId: String,
)

fun NavGraphBuilder.chatScreen(navController: NavController) {
    composable<ChatScreenDestination> { backStackEntry ->
        val param: ChatScreenDestination = backStackEntry.toRoute()
        ChatScreen(
            navController = navController,
            chatId = param.chatId,
        )
    }
}
