package com.example.movieappcompose.presentation.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.data.local.MovieEntity
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
class DetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
): ViewModel() {

    private val _movieDetails: MutableStateFlow<MovieDetailsState> =
        MutableStateFlow(MovieDetailsState())
    val movieDetails = _movieDetails.asStateFlow()




    fun onEvent(event: DetailsEvent) {
        when(event) {
            is DetailsEvent.UpsertMovie -> {
                upsertMovie(event.movieEntity)
            }
        }
    }

     fun getMovieDetails(
        type: String,
        id: Int,
        page: Int
    ) {
        viewModelScope.launch {
            movieRepository.getSimilarList(type, id, page).collectLatest { result ->
                when(result) {
                    is Resource.Error -> {
                        _movieDetails.update { it.copy(isLoading = false, error = result.message) }
                    }
                    is Resource.Loading -> {
                        _movieDetails.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        result.data?.let { movie ->
                            _movieDetails.update { it.copy(similarMovie = movie) }
                        }
                    }
                }
            }
        }
    }
    private fun upsertMovie(movieEntity: MovieEntity) {
        viewModelScope.launch {
            movieRepository.upsertMovie(movieEntity)
        }
    }


}













