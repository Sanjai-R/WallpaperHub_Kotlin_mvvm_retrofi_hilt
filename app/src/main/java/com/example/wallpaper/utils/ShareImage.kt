package com.example.wallpaper.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.net.URL

fun shareImageIntent(context: Context, url: String) {
    try {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL(url)
            val connection = url.openConnection()
            connection.connect()
            val inputStream = connection.getInputStream()
            val directory = context.getExternalFilesDir(null)
            val file = File(directory, "shared_image.jpg")

            FileOutputStream(file).use { output ->
                val buffer = ByteArray(4 * 1024)
                var byteCount: Int
                while (inputStream.read(buffer).also { byteCount = it } != -1) {
                    output.write(buffer, 0, byteCount)
                }
            }

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/*"
            val uri = FileProvider.getUriForFile(context, context.packageName + ".provider", file)
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            context.startActivity(Intent.createChooser(intent, "Share Image"))
        }
    } catch (e: Exception) {
        println(e)
    }
}