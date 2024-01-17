package com.example.movieappcompose.presentation.search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.domain.repository.MovieRepository
import com.example.movieappcompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel(){




    private val _searchState: MutableStateFlow<SearchState> =
        MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()


    fun onEvent(event: SearchEvent) {
        when(event) {

            is SearchEvent.OnSearchQueryChanged -> {
                _searchState.update { it.copy(searchQuery = event.query) }
            }
            SearchEvent.TriggerSearch -> {
                getSearchList()
            }

            is SearchEvent.ChangeActiveState -> {
                _searchState.update { it.copy(isActive = event.isActive) }
            }

            SearchEvent.RemoveSearchQuery -> {
                _searchState.update { it.copy(searchQuery = "") }
            }
        }
    }




    private fun getSearchList() {
        viewModelScope.launch {
            repository.getSearchList(
                query = _searchState.value.searchQuery,
                page = 1
            ).collectLatest { result ->
                when(result) {
                    is Resource.Error -> {
                        _searchState.update { it.copy(error = result.message, isLoading = false) }
                    }
                    is Resource.Loading -> {
                        _searchState.update { it.copy(isLoading = false) }
                    }
                    is Resource.Success -> {
                        result.data?.let { media ->
                            _searchState.update { it.copy(searchList = media) }
                        }
                    }
                }
            }
        }
    }

}







