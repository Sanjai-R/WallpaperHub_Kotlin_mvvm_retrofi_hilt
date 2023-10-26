package com.example.wallpaper.utils

import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

fun setWallPaperMobile(context: Context, url: String, coroutineScope: CoroutineScope) {
    val wallpaperManager = WallpaperManager.getInstance(context)
    try {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL(url)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            wallpaperManager.setBitmap(bitmap)
            coroutineScope.launch {
                Toast.makeText(context, "Wallpaper set successfully", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    } catch (e: Exception) {
        println(e)
    }
}