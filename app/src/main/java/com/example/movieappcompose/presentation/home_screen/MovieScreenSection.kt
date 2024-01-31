package com.example.movieappcompose.presentation.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieappcompose.domain.model.Media
import com.example.movieappcompose.presentation.common.Item
import com.example.movieappcompose.presentation.state_event.MovieState
import com.example.movieappcompose.utils.Constant


@Composable
fun MovieScreenSection(
    type: String,
    movieState: MovieState,
    navController: NavController,
    seeAll: () -> Unit
) {


    val title = when (type) {
        Constant.trendingAllListScreen -> {
            "Trending Now"
        }

        Constant.tvSeriesScreen -> {
            "Tv Series"
        }

        else -> ""
    }

    val mediaList = when (type) {
        Constant.trendingAllListScreen -> {
            movieState.trendingMovieList.take(10)
        }

        Constant.tvSeriesScreen -> {
            movieState.topRatedSeriesList.take(10)
        }

        else -> emptyList()
    }



    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
            )

            Text(
                modifier = Modifier
                    .alpha(0.7f)
                    .clickable { seeAll() },
                text = "See All",
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 14.sp,
            )

        }

        LazyRow {
            items(
                mediaList.size,
                key =  {
                    mediaList[it].id
                }
            ) {
                var endPadding = 0.dp

                if (it == mediaList.size - 1) {
                    endPadding = 16.dp
                }

                Item(
                    media = mediaList[it],
                    modifier = Modifier
                        .width(150.dp)
                        .height(200.dp)
                        .padding(start = 16.dp, end = endPadding),
                    navController = navController
                )
            }

        }
    }
}









