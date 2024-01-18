package com.example.movieappcompose.presentation.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieappcompose.domain.model.Media
import com.example.movieappcompose.presentation.details_screen.DetailsScreen
import com.example.movieappcompose.presentation.home_screen.HomeScreen
import com.example.movieappcompose.presentation.search_screen.SearchScreen
import com.example.movieappcompose.utils.Screens


@Composable
fun NavGraph(
    navController: NavHostController,
) {


    NavHost(
        navController = navController,
        startDestination = Screens.Home.rout
    ) {

        composable(route = Screens.Home.rout) {
            HomeScreen(navHostController = navController)
        }

        composable(route = Screens.Details.rout) {
            val mediaDetails =
                navController.previousBackStackEntry?.savedStateHandle?.get<Media>(
                    "media"
                )
            DetailsScreen(mediaDetails)
        }

        composable(route = Screens.SearchScreen.rout) {
            SearchScreen(navController)
        }


    }


}













