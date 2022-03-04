package com.exercise.testcompose.presentation.routing

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    object Payment : Screen("payment")
    object ThreeDS : Screen("threeDS/{url}") {
        const val urlKey = "url"
        fun createRoute(url: String) : String {
            val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
            return "threeDS/$encodedUrl"
        }
    }
}
