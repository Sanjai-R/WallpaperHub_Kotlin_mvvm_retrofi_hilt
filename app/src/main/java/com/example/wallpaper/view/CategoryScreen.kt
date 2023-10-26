package com.example.wallpaper.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallpaper.components.PhotoContainer
import com.example.wallpaper.viewModel.PhotoViewModel

@Composable
fun CategoryScreen(category: String,navController: NavController) {

    val photoViewModel = hiltViewModel<PhotoViewModel>()
    LaunchedEffect(key1 = true, block = {
        photoViewModel.getCategoryPhotos(category = category)
    })
    Column(

    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

        ){

            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                )

            }
            Text(text = category, style = MaterialTheme
                .typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground)


        }
        if (photoViewModel.loading) {
            Text(text = "Loading")
        } else {
            PhotoContainer(data = photoViewModel.allPhotos,navController)
        }
    }


}