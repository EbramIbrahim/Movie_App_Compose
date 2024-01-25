package com.example.movieappcompose.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieappcompose.domain.model.Media
import com.example.movieappcompose.presentation.MovieViewModel
import com.example.movieappcompose.presentation.details_screen.DetailsScreen
import com.example.movieappcompose.presentation.home_screen.HomeScreen
import com.example.movieappcompose.presentation.popular_screen.AllMovieAndSeriesScreen
import com.example.movieappcompose.presentation.search_screen.SearchScreen
import com.example.movieappcompose.presentation.splash_screen.SplashScreen
import com.example.movieappcompose.utils.Screens


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

        composable(
            route = Screens.AllMovieAndSeriesScreen.rout + "/{type}",
            arguments = listOf(
                navArgument("type") { type = NavType.StringType }
            )
        ) { entry ->

            val viewModel =
                entry.sharedViewModel<MovieViewModel>(navController = navController)
            val movieState = viewModel.movieState.collectAsState().value

            AllMovieAndSeriesScreen(
                movieState = movieState,
                onEvent = viewModel::onEvent,
                type = entry.arguments?.getString("type")!!,
                navController = navController
            )
        }


    }
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {

    val navGraphEntry = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphEntry)
    }

    return viewModel(parentEntry)

}













