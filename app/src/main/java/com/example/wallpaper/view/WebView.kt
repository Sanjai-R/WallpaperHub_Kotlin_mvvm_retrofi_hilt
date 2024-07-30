package com.example.wallpaper.view

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun InstagramWebView(webViewClient: WebViewClient = WebViewClient()) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                this.webViewClient = webViewClient
                settings.javaScriptEnabled = true
                loadUrl("https://practicetestautomation.com/practice-test-login/")
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
@Composable
fun InstagramScreen() {
    InstagramWebView()
}

@Preview
@Composable
fun PreviewInstagramScreen() {
    InstagramScreen()
}