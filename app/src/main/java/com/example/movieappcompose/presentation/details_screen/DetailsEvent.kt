package com.example.movieappcompose.presentation.details_screen

import com.example.movieappcompose.data.local.MovieEntity

sealed interface DetailsEvent {

    data class UpsertMovie(val movieEntity: MovieEntity) : DetailsEvent

    data class DeleteMovie(val movieEntity: MovieEntity) : DetailsEvent

}