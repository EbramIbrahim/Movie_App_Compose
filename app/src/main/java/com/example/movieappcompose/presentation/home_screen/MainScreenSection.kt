package com.example.movieappcompose.presentation.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.presentation.state_event.MovieState
import com.example.movieappcompose.utils.Constant

@Composable
fun MainScreenSection(
    movieState: MovieState,
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        MovieScreenSection(
            type = Constant.trendingAllListScreen,
            movieState = movieState,
            )

        Spacer(modifier = Modifier.height(16.dp))
        
        AutoSwipeSection(movieState = movieState)

        Spacer(modifier = Modifier.height(16.dp))

        MovieScreenSection(
            type = Constant.tvSeriesScreen,
            movieState = movieState
        )

        Spacer(modifier = Modifier.height(16.dp))

    }



}