package com.example.wallpaper.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.wallpaper.components.PhotoContainer
import com.example.wallpaper.ui.theme.poppinsFont
import com.example.wallpaper.viewModel.PhotoViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(navController: NavController) {

    val vm = hiltViewModel<PhotoViewModel>()

    LaunchedEffect(true) {
        vm.getAllPhotos()
    }
    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.W800,
                fontSize = 30.sp,
                fontFamily = poppinsFont,
                )
        ) {
            append("Wallpaper")
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.W800,
                fontSize = 30.sp,
                fontFamily = poppinsFont,

            )
        ) {
            append("Hub")
        }
    }
    Column {
        Text(text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ,
            textAlign = TextAlign.Center
        )

        if (vm.loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
        } else {
            PhotoContainer(data = vm.allPhotos, navController)
        }
    }
}

//    Button(onClick = {
//        FirebaseAuth.getInstance().signOut()
//    }) {
//        Text("Logout")
//    }


