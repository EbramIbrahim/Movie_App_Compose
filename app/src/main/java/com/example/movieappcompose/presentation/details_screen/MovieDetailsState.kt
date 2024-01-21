package com.example.movieappcompose.presentation.details_screen

import com.example.movieappcompose.domain.model.Media

data class MovieDetailsState(
     val similarMovie: List<Media> = emptyList(),
     val isLoading: Boolean = false,
     val error: String? = null
)
