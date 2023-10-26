package com.example.wallpaper.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter


data class Category(
    val name: String,
    val image: String,
)

val listOfCategory = listOf<Category>(
    Category(
        "Nature",
        "https://images.pexels.com/photos/23547/pexels-photo.jpg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
    ),
    Category(
        "Cars",
        "https://images.pexels.com/photos/1719648/pexels-photo-1719648.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
    ),
    Category(
        "Heros",
        "https://images.unsplash.com/photo-1509347528160-9a9e33742cdb?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2970&q=80"
    ),

    Category(
        "Animals",
        "https://images.unsplash.com/photo-1504006833117-8886a355efbf?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2970&q=80"
    ),
    Category(
        "Sports",
        "https://images.unsplash.com/photo-1587280501635-68a0e82cd5ff?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2772&q=80"
    )

)

@Composable
fun ExploreScreen(navController: NavController) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            "Discover, Learn, and Experience More",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground
        )

        Categories(listOfCategory = listOfCategory, navController)
    }

}

@Composable
fun Categories(listOfCategory: List<Category>, navController: NavController) {

    listOfCategory.forEach {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(Color.DarkGray, shape = MaterialTheme.shapes.medium)
                .clickable {
                    navController.navigate("category/${it.name}")
                },
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = rememberImagePainter(
                    data = it.image
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(
                        MaterialTheme.shapes.medium
                    ),
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = it.name,
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()

            )

        }


    }
}