package com.example.movieappcompose.presentation.popular_screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappcompose.presentation.common.MovieItem
import com.example.movieappcompose.presentation.state_event.MovieEvent
import com.example.movieappcompose.presentation.state_event.MovieState
import com.example.movieappcompose.utils.Constant

@Composable
fun AllMovieAndSeriesScreen(
    movieState: MovieState,
    onEvent: (MovieEvent) -> Unit,
    type: String,
    navController: NavController
) {


    val media = when(type) {
        Constant.trendingAllListScreen -> {
            movieState.trendingMovieList
        }

        Constant.tvSeriesScreen -> {
            movieState.topRatedSeriesList
        }

        Constant.popularMovieScreen -> {
            movieState.popularMovieList
        }

        else -> emptyList()
    }

    if (media.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2) ,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
        ) {
            items(media.size) {index ->
                MovieItem(
                    navController = navController,
                    media = media[index]
                )
                Spacer(modifier = Modifier.height(8.dp))

                val shouldPaginate = index >= media.size -1 && !movieState.isLoading
                if(shouldPaginate) {
                    onEvent(MovieEvent.GetMovieListFromRemote(Constant.POPULAR))
                }
            }
        }
    }




}






