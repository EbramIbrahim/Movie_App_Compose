package com.example.movieappcompose.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappcompose.presentation.search_screen.SearchEvent
import com.example.movieappcompose.presentation.search_screen.SearchState
import com.example.movieappcompose.utils.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarField(
    isEnabled: Boolean,
    searchState: SearchState?,
    onEvent: (SearchEvent) -> Unit?,
    navigate:(String) -> Unit?,
    navController: NavController
) {

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 14.dp)
            .clickable { navigate(Screens.SearchScreen.rout) },
        query = searchState?.searchQuery ?: "",
        onQueryChange = { onEvent(SearchEvent.OnSearchQueryChanged(it)) },
        onSearch = {
            onEvent(SearchEvent.TriggerSearch)
        },
        active = searchState?.isActive ?: false,
        onActiveChange = {
            onEvent(SearchEvent.ChangeActiveState(it))
        },
        enabled = isEnabled,
        placeholder = {
            Text(text = "Search a Movie or tv Series")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        },

        trailingIcon = {
            if (searchState?.isActive == true) {
                Icon(
                    modifier = Modifier.clickable {
                        if (searchState.searchQuery.isNotEmpty()){
                            onEvent(SearchEvent.RemoveSearchQuery)
                        } else {
                            onEvent(SearchEvent.ChangeActiveState(false))
                        }
                    },
                    imageVector = Icons.Filled.Close,
                    contentDescription = null
                )
            }
        },
    ) {



        if (searchState?.isLoading == true) {
            Box(modifier = Modifier.fillMaxSize()) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
            AnimatedVisibility(visible = !searchState?.isLoading!!) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {

                    items(searchState.searchList.size) { index ->
                        MovieItem(media = searchState.searchList[index], navController = navController)
                    }
                }
            }



    }


}





