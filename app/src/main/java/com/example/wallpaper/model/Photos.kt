package com.example.wallpaper.model

data class Photos(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val prev_page: String,
    val total_results: Int
)