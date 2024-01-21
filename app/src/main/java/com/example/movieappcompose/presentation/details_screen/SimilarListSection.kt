package com.example.movieappcompose.presentation.details_screen

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
import com.example.movieappcompose.presentation.common.Item


@Composable
fun SimilarListSection(
    detailsState: MovieDetailsState,
    navController: NavController
) {


    val similarList = detailsState.similarMovie.take(10)

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Similar",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
            )

            Text(
                modifier = Modifier.alpha(0.7f),
                text = "See All",
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 14.sp,
            )

        }

        LazyRow {

            items(similarList.size) { index ->

                var endPadding = 0.dp

                if (index == similarList.size - 1){
                    endPadding = 16.dp
                }

                Item(
                    media = detailsState.similarMovie[index],
                    modifier = Modifier
                        .height(200.dp)
                        .width(150.dp)
                        .padding(start = 16.dp, end = endPadding),
                    navController = navController
                )
            }
        }

    }


}









