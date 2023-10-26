package com.example.wallpaper.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.wallpaper.model.Photo
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun PhotoContainer(data:List<Photo>,navController: NavController) {
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), content = {
        items(data.size) { index ->
            Image(
                painter = rememberImagePainter(
                    data = data[index].src.large,
                ),
                contentDescription = "My content description",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(4.dp)
                    .clip(MaterialTheme.shapes.medium).clickable(
                        onClick = {
                            val encodedUrl = URLEncoder.encode(data[index].src.large, StandardCharsets.UTF_8.toString())
                            navController.navigate("photoDetail/${encodedUrl}")

                        }
                    ),
                contentScale = ContentScale.FillBounds
            )
        }

    })
}