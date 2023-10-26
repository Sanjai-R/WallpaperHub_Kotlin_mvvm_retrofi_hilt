package com.example.wallpaper.utils

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.net.URL

fun saveImageToInternalStorage(context: Context, imageUrl: String, coroutineScope: CoroutineScope) {
     try {
         CoroutineScope(Dispatchers.IO).launch {
             val fileName = "my_image.jpg"
             val directory = File(context.filesDir, "images")

             if (!directory.exists()) {
                 directory.mkdirs()
             }

             val file = File(directory, fileName)

             val inputStream = URL(imageUrl).openStream()
             val outputStream = FileOutputStream(file)
             val buffer = ByteArray(4 * 1024)
             var bytesRead: Int
             while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                 outputStream.write(buffer, 0, bytesRead)
             }

             inputStream.close()
             outputStream.close()
             coroutineScope.launch {
                 Toast.makeText(context, "Image saved successfully", Toast.LENGTH_SHORT).show()
             }
         }

     }
     catch (e: Exception) {
         println(e)
     }
}