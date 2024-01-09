package com.example.movieappcompose.presentation.state_event

sealed interface MovieEvent {


    object InitialProcesses: MovieEvent
    data class GetMovieListFromRemote(val category: String): MovieEvent
}