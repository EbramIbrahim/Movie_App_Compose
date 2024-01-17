package com.example.movieappcompose.utils

sealed class Screens(val rout: String) {
    object Home : Screens("main")
    object PopularMovieList : Screens("popularMovie")
    object UpcomingMovieList : Screens("upcomingMovie")
    object Details : Screens("details")
    object SearchScreen: Screens("search_screen")

}