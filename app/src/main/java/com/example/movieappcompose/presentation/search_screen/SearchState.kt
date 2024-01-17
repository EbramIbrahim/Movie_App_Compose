package com.example.movieappcompose.presentation.search_screen

import com.example.movieappcompose.domain.model.Media

data class SearchState(
    val searchList: List<Media> = emptyList(),
    var searchQuery: String = "",
    val isActive: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
