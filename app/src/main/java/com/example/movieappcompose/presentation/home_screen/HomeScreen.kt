package com.example.movieappcompose.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieappcompose.domain.model.BottomNavItems
import com.example.movieappcompose.presentation.MovieViewModel
import com.example.movieappcompose.presentation.common.BottomNavBar
import com.example.movieappcompose.presentation.popular_screen.AllMovieAndSeriesScreen
import com.example.movieappcompose.utils.Constant.popularMovieScreen
import com.example.movieappcompose.utils.Screens


@Composable
fun HomeScreen(
    navHostController: NavHostController,
) {

    val viewModel = hiltViewModel<MovieViewModel>()
    val movieState = viewModel.movieState.collectAsState().value
    val bottomNavController = rememberNavController()


    Scaffold(
        bottomBar = {
            BottomNavBar(
                bottomItems = listOf(
                    BottomNavItems(
                        title = "Home",
                        icon = Icons.Rounded.Home,
                        route = Screens.MainScreen.rout
                    ),
                    BottomNavItems(
                        title = "Popular",
                        icon = Icons.Rounded.Movie,
                        route = Screens.AllMovieAndSeriesScreen.rout
                    ),

                    ),
                onItemClick = {
                    bottomNavController.navigate(it)
                },
                navController = bottomNavController
            )
        }

    ) {

        Box(
            modifier = Modifier
                .padding(it)
        ) {
            NavHost(
                navController = bottomNavController,
                startDestination = Screens.MainScreen.rout,
                route = "Main_Root"
            ) {
                composable(route = Screens.MainScreen.rout) {
                    MainScreenSection(movieState = movieState, navController = navHostController)
                }

                composable(route = Screens.AllMovieAndSeriesScreen.rout) {
                    AllMovieAndSeriesScreen(
                        movieState = movieState,
                        onEvent = viewModel::onEvent,
                        type = popularMovieScreen,
                        navController = navHostController
                    )
                }

            }
        }

    }


}








