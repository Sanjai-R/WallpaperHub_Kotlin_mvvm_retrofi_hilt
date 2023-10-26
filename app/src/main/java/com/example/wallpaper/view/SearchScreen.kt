package com.example.wallpaper.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallpaper.components.InputField
import com.example.wallpaper.components.PhotoContainer
import com.example.wallpaper.components.ReusableTextField
import com.example.wallpaper.ui.theme.poppinsFont
import com.example.wallpaper.view.auth.Input
import com.example.wallpaper.viewModel.PhotoViewModel

@Composable
fun SearchScreen(navController: NavController) {
    val photoViewModel = hiltViewModel<PhotoViewModel>()
    var query by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true, block = {
        photoViewModel.getAllPhotos()
    })
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        val input = Input(
            label = "Email address",
            value = query,
            type = "email",
            error = "",
            isPassword = false,
            hint = "Search anything"
        )
        searchField(input = input,photoViewModel, onValueChange = {
            query = it
        })
        if(photoViewModel.loading){
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
        }else{
            PhotoContainer(data = photoViewModel.allPhotos, navController = navController)
        }


    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchField(input: Input,photoViewModel:PhotoViewModel, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        placeholder = {
            Text(
                text = input.hint,
                fontFamily = poppinsFont,
                color = Color(0xFFA0A3BD),
                fontSize = 14.sp
            )
        },
        value = input.value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFF3F5F9),
            unfocusedBorderColor = Color.Transparent,
        ),
        trailingIcon = {
            IconButton(onClick = {
                photoViewModel.getCategoryPhotos(input.value)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search"
                )
            }
        }

    )

}