package com.example.movieappcompose.utils

sealed class Screens(val rout: String) {

    object SplashScreen: Screens("splash")
    object Home : Screens("home")
    object MainScreen: Screens("main_screen")
    object AllMovieAndSeriesScreen : Screens("see_all")
    object PopularScreen : Screens("PopularScreen")
    object Details : Screens("details")
    object SearchScreen: Screens("search_screen")
    object Favorite: Screens("favorite_screen")

}