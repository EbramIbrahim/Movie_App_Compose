package com.example.movieappcompose.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappcompose.presentation.state_event.MovieState
import com.example.movieappcompose.utils.Constant
import com.example.movieappcompose.utils.Screens

@Composable
fun MainScreenSection(
    movieState: MovieState,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
    ) {

        Spacer(modifier = Modifier.height(4.dp))

        if (movieState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.Center)
                        .scale(0.5f)
                )
            }
        } else {

            MovieScreenSection(
                type = Constant.trendingAllListScreen,
                movieState = movieState,
                navController = navController,
                seeAll = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "mediaList", movieState.trendingMovieList
                    )
                    navController.navigate(Screens.AllMovieAndSeriesScreen.rout)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            AutoSwipeSection(movieState = movieState, navController = navController)

            Spacer(modifier = Modifier.height(16.dp))

            MovieScreenSection(
                type = Constant.tvSeriesScreen,
                movieState = movieState,
                navController = navController,
                seeAll = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "mediaList", movieState.topRatedSeriesList
                    )
                    navController.navigate(Screens.AllMovieAndSeriesScreen.rout)

                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}