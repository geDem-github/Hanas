package com.example.hanas.android.ui.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hanas.android.ui.feature.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeScreenDestination

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable<HomeScreenDestination> {
        HomeScreen(navController = navController)
    }
}
