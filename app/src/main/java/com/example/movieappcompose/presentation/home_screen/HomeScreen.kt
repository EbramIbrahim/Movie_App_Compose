package com.example.movieappcompose.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


@OptIn(ExperimentalMaterial3Api::class)
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
                        icon = Icons.Rounded.Movie,
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
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (
                            bottomNavController.currentBackStackEntry?.destination?.route ==
                            Screens.PopularMovieList.rout
                        ) "Popular Movie"
                        else "Upcoming Movie",
                        fontSize = 20.sp
                    )
                },
                modifier = Modifier.shadow(2.dp),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    MaterialTheme.colorScheme.inverseOnSurface
                )
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
                    MainScreenSection(movieState = movieState)
                }

                composable(route = Screens.UpcomingMovieList.rout) {
                    UpcomingScreen(
                        navController = navHostController,
                        movieState = movieState,
                        onEvent = viewModel::onEvent
                    )
                }

            }
        }

    }


}








