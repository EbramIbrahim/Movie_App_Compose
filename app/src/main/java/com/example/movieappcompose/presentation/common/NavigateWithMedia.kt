package com.example.movieappcompose.presentation.common

import androidx.navigation.NavController
import com.example.movieappcompose.domain.model.Media
import com.example.movieappcompose.utils.Screens


val navigateWithMedia: (
    navController: NavController,
    media: Media?
) -> Unit = { navController, media ->

    navController.currentBackStackEntry?.savedStateHandle?.set(
        "media", media
    )
    navController.navigate(Screens.Details.rout)
}