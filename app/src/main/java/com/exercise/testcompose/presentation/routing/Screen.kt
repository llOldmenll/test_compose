package com.exercise.testcompose.presentation.routing

sealed class Screen(val route: String) {
    object Payment : Screen("payment")
}
