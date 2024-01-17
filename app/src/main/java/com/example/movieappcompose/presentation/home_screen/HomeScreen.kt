package com.example.movieappcompose.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Upcoming
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
import com.example.movieappcompose.presentation.UpcomingScreen
import com.example.movieappcompose.presentation.common.BottomNavBar
import com.example.movieappcompose.utils.Screens


@Composable
fun HomeScreen(navHostController: NavHostController) {


    val viewModel = hiltViewModel<MovieViewModel>()
    val movieState = viewModel.movieState.collectAsState().value
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                bottomItems = listOf(
                    BottomNavItems(
                        title = "Popular",
                        icon = Icons.Rounded.Home,
                        route = Screens.PopularMovieList.rout
                    ),
                    BottomNavItems(
                        title = "Upcoming",
                        icon = Icons.Rounded.Upcoming,
                        route = Screens.UpcomingMovieList.rout
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
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(
                navController = bottomNavController,
                startDestination = Screens.PopularMovieList.rout
            ) {
                composable(route = Screens.PopularMovieList.rout) {
                    MainScreenSection(movieState = movieState, navController = navHostController)
                }

                composable(route = Screens.UpcomingMovieList.rout) {
                    UpcomingScreen(
                        movieState = movieState,
                        onEvent = viewModel::onEvent
                    )
                }

            }
        }

    }


}








