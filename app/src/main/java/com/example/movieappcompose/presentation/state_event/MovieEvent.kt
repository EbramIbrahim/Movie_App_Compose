package com.example.movieappcompose.presentation.state_event

import com.example.movieappcompose.data.local.MovieEntity

sealed interface MovieEvent {


    object InitialProcesses: MovieEvent
    data class GetMovieListFromRemote(val category: String): MovieEvent
    object GetFavoriteMovieList: MovieEvent
    object GetWatchedMovieList: MovieEvent

}