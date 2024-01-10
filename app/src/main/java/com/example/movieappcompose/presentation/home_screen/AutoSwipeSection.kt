package com.example.movieappcompose.presentation.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieappcompose.presentation.common.AutoSwipeImagePager
import com.example.movieappcompose.presentation.state_event.MovieState

@Composable
fun AutoSwipeSection(
    movieState: MovieState
) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Special",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
            )
            Icon(
                imageVector = Icons.Rounded.TrendingUp,
                contentDescription = null
            )

        }

        AutoSwipeImagePager(
            mediaList = movieState.trendingMovieList.shuffled().take(7),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp)
        )

    }

}







