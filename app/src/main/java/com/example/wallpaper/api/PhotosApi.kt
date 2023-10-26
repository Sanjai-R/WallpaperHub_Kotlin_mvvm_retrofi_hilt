package com.example.wallpaper.api

import com.example.wallpaper.EnvParameters
import com.example.wallpaper.model.Photos
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PhotosApi {
    @GET(EnvParameters.CURATED_ENDPOINT)
    suspend fun getPhotos(
        @Header("Authorization") authHeader: String = EnvParameters.API_KEY
    ): Photos

    @GET("search")
    suspend fun getCategoryPhotos(
        @Query("query") category: String,
        @Header("Authorization") authHeader: String = EnvParameters.API_KEY
    ): Photos
}