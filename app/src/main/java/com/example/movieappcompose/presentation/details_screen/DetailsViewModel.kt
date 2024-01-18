package com.example.movieappcompose.presentation.details_screen

import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
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
class DetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _movieDetails: MutableStateFlow<MovieDetailsState> =
        MutableStateFlow(MovieDetailsState())
    val movieDetails = _movieDetails.asStateFlow()

    private val movieId = savedStateHandle.get<Int>("movieId")

    init {
        getMovieDetails(movieId ?: -1)
    }



    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getMovieListById(movieId).collectLatest { result ->
                when(result) {
                    is Resource.Error -> {
                        _movieDetails.update { it.copy(isLoading = false) }
                    }
                    is Resource.Loading -> {
                        _movieDetails.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        result.data?.let { movie ->
//                            _movieDetails.update { it.copy(movie = movie) }
                        }
                    }
                }
            }
        }



    }

}













