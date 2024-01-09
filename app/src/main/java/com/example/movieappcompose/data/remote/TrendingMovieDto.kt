package com.example.movieappcompose.data.remote

data class TrendingMovieDto(
    val page: Int,
    val results: List<TrendingDto>,
    val total_pages: Int,
    val total_results: Int
)