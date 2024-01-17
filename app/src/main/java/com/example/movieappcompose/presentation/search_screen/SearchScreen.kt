package com.example.movieappcompose.presentation.search_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieappcompose.presentation.common.SearchBarField

@Composable
fun SearchScreen() {
    
    val viewModel = hiltViewModel<SearchViewModel>()
    val searchState by viewModel.searchState.collectAsState()

    Box(modifier = Modifier.fillMaxWidth()) {
        SearchBarField(
            isEnabled = true,
            searchState = searchState,
            onEvent = viewModel::onEvent,
            navigate = { null }
        )
    }
    

}