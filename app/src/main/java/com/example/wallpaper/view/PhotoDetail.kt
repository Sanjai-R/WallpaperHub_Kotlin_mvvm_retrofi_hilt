package com.example.wallpaper.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.wallpaper.utils.saveImageToInternalStorage
import com.example.wallpaper.utils.setWallPaperMobile
import com.example.wallpaper.utils.shareImageIntent
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Camera
import compose.icons.evaicons.fill.Download
import compose.icons.evaicons.fill.Share


data class PhotoIcon(
    val icon: ImageVector,
    val onClick: () -> Unit = {},
)


@OptIn(ExperimentalCoilApi::class)
@Composable
fun PhotoDetail(url: String, navController: NavController) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var listOfIconButton = listOf<PhotoIcon>(
        PhotoIcon(icon = EvaIcons.Fill.Download) {
            saveImageToInternalStorage(context, url, coroutineScope)
        },
        PhotoIcon(icon = EvaIcons.Fill.Share, onClick = {
            shareImageIntent(context, url)
        }),
        PhotoIcon(icon = EvaIcons.Fill.Camera, onClick = {
            setWallPaperMobile(context, url, coroutineScope)
        }),
    )

    Box(
        Modifier.fillMaxSize()
    ) {

        Image(
            rememberImagePainter(data = url),
            contentDescription = "My content description",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = androidx.compose.ui.graphics.Color.White
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                listOfIconButton.forEach { it ->
                    IconButton(
                        onClick = {
                            it.onClick()
                        },
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(50))
                            .background(Color(0x34E4E3E3))
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = "Back",
                            tint = androidx.compose.ui.graphics.Color.White
                        )

                    }
                }

            }
        }


    }

}