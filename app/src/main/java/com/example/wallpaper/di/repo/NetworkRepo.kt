package com.example.wallpaper.di.repo

import com.example.wallpaper.api.PhotosApi
import com.example.wallpaper.model.Photos
import javax.inject.Inject

class NetworkRepo @Inject constructor(
    private val photosApi: PhotosApi
){
    suspend fun getAllPhotos(): Photos {
        return photosApi.getPhotos()
    }

    suspend fun getCategoryPhotos(category: String): Photos {
        return photosApi.getCategoryPhotos(category)
    }

}