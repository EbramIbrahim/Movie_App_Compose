package com.example.movieappcompose.presentation.common

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieappcompose.domain.model.Media
import com.example.movieappcompose.presentation.details_screen.DetailsScreen
import com.example.movieappcompose.presentation.home_screen.HomeScreen
import com.example.movieappcompose.presentation.all_move_series.AllMovieAndSeriesScreen
import com.example.movieappcompose.presentation.search_screen.SearchScreen
import com.example.movieappcompose.presentation.splash_screen.SplashScreen
import com.example.movieappcompose.utils.Screens


@SuppressLint("SuspiciousIndentation")
@Composable
fun NavGraph(
    navController: NavHostController,
) {


    NavHost(
        navController = navController,
        startDestination = "Main_Root"
    ) {

        composable(route = Screens.SplashScreen.rout) {
            SplashScreen(navController = navController)
        }


        composable(route = "Main_Root") {
            HomeScreen(
                navHostController = navController
            )
        }

        composable(route = Screens.Details.rout) {
            val mediaDetails =
                navController.previousBackStackEntry?.savedStateHandle?.get<Media>(
                    "media"
                )
            DetailsScreen(mediaDetails, navController)
        }

        composable(route = Screens.SearchScreen.rout) {
            SearchScreen(navController)
        }

        composable(route = Screens.AllMovieAndSeriesScreen.rout) {
            val mediaList =
                navController.previousBackStackEntry?.savedStateHandle?.get<List<Media>>(
                    "mediaList"
                )
                AllMovieAndSeriesScreen(
                    media = mediaList,
                    navController = navController
                )

        }


    }
}














