package com.example.movieappcompose.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItems(
    val title: String,
    val icon: ImageVector,
    val route: String
)
