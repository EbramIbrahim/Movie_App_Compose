package com.example.movieappcompose.presentation.search_screen

sealed interface SearchEvent {

    object TriggerSearch: SearchEvent
    object RemoveSearchQuery: SearchEvent
    data class OnSearchQueryChanged(val query: String): SearchEvent
    data class ChangeActiveState(val isActive: Boolean): SearchEvent
}