package com.example.movieappcompose.presentation.state_event

import com.example.movieappcompose.domain.model.Media

data class MovieState(

    val popularMovieList: List<Media> = emptyList(),
    val upcomingMovieList: List<Media> = emptyList(),
    val trendingMovieList: List<Media> = emptyList(),
    val specialMovieList: List<Media> = emptyList(),
    val topRatedSeriesList: List<Media> = emptyList(),
    val favoriteMovieList: List<Media> = emptyList(),
    val popularMoviePage: Int = 1,
    val upcomingMoviePage: Int = 1,
    val isLoading: Boolean = false,
    val error: String? = null
)










