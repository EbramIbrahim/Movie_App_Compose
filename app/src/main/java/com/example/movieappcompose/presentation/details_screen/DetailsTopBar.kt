package com.example.movieappcompose.presentation.details_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.WatchLater
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DetailsTopBar(
    onWatchedClicked: () -> Unit,
    onFavoriteClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
) {


    TopAppBar(
        title = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(11.dp)),
        navigationIcon = {
            IconButton(onClick = { onBackButtonClicked() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { onFavoriteClicked() }) {
                Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null)
            }
            IconButton(onClick = { onWatchedClicked() }) {
                Icon(imageVector = Icons.Rounded.WatchLater, contentDescription = null)
            }
        },
        contentColor = MaterialTheme.colorScheme.secondaryContainer,
        backgroundColor = Color.Transparent,
    )


}
















