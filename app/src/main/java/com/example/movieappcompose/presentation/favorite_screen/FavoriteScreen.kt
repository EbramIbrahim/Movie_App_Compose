package com.example.movieappcompose.presentation.favorite_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieappcompose.presentation.common.AutoSwipeImagePager
import com.example.movieappcompose.presentation.state_event.MovieState

@Composable
fun FavoriteScreen(
    movieState: MovieState,
    navController: NavController
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 16.dp)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Watch List",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
            )
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = Color.Yellow
            )

        }

        AutoSwipeImagePager(
            mediaList = movieState.watchedMovieList,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp),
            navController = navController
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Favorite",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
            )
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = null,
                tint = Color.Red
            )

        }

        AutoSwipeImagePager(
            mediaList = movieState.favoriteMovieList,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp),
            navController = navController
        )


        Spacer(modifier = Modifier.height(32.dp))

    }


}