package com.example.movieappcompose.presentation.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappcompose.presentation.common.SearchBarField
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

        SearchBarField(
            isEnabled = false,
            searchState = null,
            onEvent = { null },
            navigate = {
                navController.navigate(it)
            },
            navController
        )

        Spacer(modifier = Modifier.height(8.dp))

        MovieScreenSection(
            type = Constant.trendingAllListScreen,
            movieState = movieState,
            navController = navController,
            seeAll = {
                navController.navigate(Screens.AllMovieAndSeriesScreen.rout + "/{$it}")
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
                navController.navigate(Screens.AllMovieAndSeriesScreen.rout + "/{$it}")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

    }


}