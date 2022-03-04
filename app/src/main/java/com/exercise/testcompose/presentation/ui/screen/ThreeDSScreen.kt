package com.exercise.testcompose.presentation.ui.screen

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.exercise.testcompose.entity.RedirectUrl
import com.exercise.testcompose.presentation.view_model.ThreeDSViewModel

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ThreeDSScreen(url: String, navController: NavController, viewModel: ThreeDSViewModel = hiltViewModel()) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return when (viewModel.checkRedirectUrl(request?.url.toString())) {
                        RedirectUrl.SUCCESS -> routeToNextScreen(navController, true)
                        RedirectUrl.FAILURE -> routeToNextScreen(navController, false)
                        else -> super.shouldOverrideUrlLoading(view, request)
                    }
                }
            }
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    })
}

private fun routeToNextScreen(navController: NavController, isSuccess: Boolean): Boolean {
    navController.popBackStack()
    if (isSuccess) {
        // TODO attach success screen
    } else {
        // TODO attach failure screen
    }
    return false
}