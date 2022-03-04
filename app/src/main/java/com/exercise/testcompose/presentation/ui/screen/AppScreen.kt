package com.exercise.testcompose.presentation.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.exercise.testcompose.presentation.routing.Screen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Payment.route) {
        composable(route = Screen.Payment.route) { PaymentScreen(navController) }

        composable(
            route = Screen.Status.route,
            arguments = listOf(navArgument(Screen.Status.statusKey) { type = NavType.BoolType })
        ) {
            val status = it.arguments?.getBoolean(Screen.Status.statusKey) ?: false
            StatusScreen(status, navController)
        }

        composable(
            route = Screen.ThreeDS.route,
            arguments = listOf(navArgument(Screen.ThreeDS.urlKey) { type = NavType.StringType })
        ) {
            val url = it.arguments?.getString(Screen.ThreeDS.urlKey)
            requireNotNull(url) { "Url can't be null." }
            ThreeDSScreen(url, navController)
        }
    }
}