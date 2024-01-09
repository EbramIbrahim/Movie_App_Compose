package com.example.movieappcompose.presentation.state_event

import com.example.movieappcompose.domain.model.Movie

data class MovieState(

    val popularMovieList: List<Movie> = emptyList(),
    val upcomingMovieList: List<Movie> = emptyList(),
    val popularMoviePage: Int = 1,
    val upcomingMoviePage: Int = 1,
    val isLoading: Boolean = false,
    val error: String? = null
)









