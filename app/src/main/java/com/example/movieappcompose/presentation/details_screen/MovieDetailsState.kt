package com.example.movieappcompose.presentation.details_screen

import com.example.movieappcompose.domain.model.Movie

data class MovieDetailsState(
     val movie: Movie? = null,
     val isLoading: Boolean = false
)
