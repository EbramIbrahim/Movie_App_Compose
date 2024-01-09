package com.example.movieappcompose.data.remote

data class MovieDtoResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)