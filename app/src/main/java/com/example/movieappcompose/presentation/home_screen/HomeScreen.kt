package com.example.movieappcompose.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieappcompose.domain.model.BottomNavItems
import com.example.movieappcompose.presentation.MovieViewModel
import com.example.movieappcompose.presentation.common.BottomNavBar
import com.example.movieappcompose.presentation.favorite_screen.FavoriteScreen
import com.example.movieappcompose.presentation.all_move_series.AllMovieAndSeriesScreen
import com.example.movieappcompose.presentation.common.SearchBarField
import com.example.movieappcompose.utils.Screens


@Composable
fun HomeScreen(
    navHostController: NavHostController,
) {

    val viewModel = hiltViewModel<MovieViewModel>()
    val movieState = viewModel.movieState.collectAsStateWithLifecycle().value
    val bottomNavController = rememberNavController()


    Scaffold(
        topBar =  {
            SearchBarField(
                isEnabled = false,
                searchState = null,
                onEvent = { null },
                navigate = {
                    navHostController.navigate(it)
                },
                navHostController
            )
        },
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

                    BottomNavItems(
                        title = "Favorite",
                        icon = Icons.Rounded.Favorite,
                        route = Screens.Favorite.rout
                    ),
                    ),
                onItemClick = {
                    bottomNavController.navigate(it) {
                        popUpTo(bottomNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
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
                        media = movieState.popularMovieList,
                        navController = navHostController
                    )
                }

                composable(route = Screens.Favorite.rout) {
                    FavoriteScreen(
                        movieState = movieState,
                        navController = navHostController
                    )
                }

            }
        }

    }


}








