package com.example.wallpaper.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaper.di.repo.NetworkRepo
import com.example.wallpaper.model.Photo
import com.example.wallpaper.model.Photos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val networkRepo: NetworkRepo,
) : ViewModel() {
    var category: String by mutableStateOf("")
    var allPhotos: List<Photo> by mutableStateOf(emptyList<Photo>())
    var loading by mutableStateOf(false)
    fun getAllPhotos() {

        loading = true
        viewModelScope.launch {
            Log.d("abc123TAG", "getMovieList: ")
            try {
                val response = networkRepo.getAllPhotos()
                allPhotos = response.photos
                loading = false


            } catch (e: Exception) {
                loading = false
                Log.d("abc123TAG", "getMovieList: ${e.message})")
            }

        }
    }

    fun getCategoryPhotos(category: String) {
        loading = true
        viewModelScope.launch {
            try {

                val response = networkRepo.getCategoryPhotos(category)
                allPhotos = response.photos
                loading = false
            } catch (e: Exception) {
                loading = false
                Log.d("abc123TAG", "getMovieList: ${e.message})")
            }
        }
    }
}