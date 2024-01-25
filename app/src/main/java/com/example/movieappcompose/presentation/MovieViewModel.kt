package com.example.movieappcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.domain.repository.MovieRepository
import com.example.movieappcompose.presentation.state_event.MovieEvent
import com.example.movieappcompose.presentation.state_event.MovieState
import com.example.movieappcompose.utils.Constant
import com.example.movieappcompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {


    private val _movieState: MutableStateFlow<MovieState> =
        MutableStateFlow(MovieState())
    val movieState = _movieState.asStateFlow()

    init {
        onEvent(MovieEvent.InitialProcesses)
    }


    fun onEvent(event: MovieEvent) {
        when (event) {
            is MovieEvent.GetMovieListFromRemote -> {
                getPopularMovieList()
            }

            MovieEvent.InitialProcesses -> {
                getPopularMovieList()
//              getUpcomingMovieList(false)
                getTrendingMovieList()
                getTopRatedSeries()
            }
        }
    }


    private fun getPopularMovieList() {
        viewModelScope.launch {
            repository.getMovieList(
                category = Constant.POPULAR,
                page = _movieState.value.popularMoviePage,
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _movieState.update { it.copy(error = result.message, isLoading = false) }
                    }

                    is Resource.Loading -> {
                        _movieState.update { it.copy(isLoading = result.isLoading) }
                    }

                    is Resource.Success -> {
                        result.data?.let { popularMovie ->
                            _movieState.update {
                                it.copy(
                                    popularMovieList = _movieState.value.popularMovieList +
                                            popularMovie.shuffled(),
                                    popularMoviePage = _movieState.value.popularMoviePage + 1,
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
            }
        }
    }

//    private fun getUpcomingMovieList(shouldFetchFromRemote: Boolean) {
//        viewModelScope.launch {
//
//            repository.getMovieListByCategory(
//                category = Constant.UPCOMING,
//                page = _movieState.value.upcomingMoviePage,
//                shouldFetchFromRemote = shouldFetchFromRemote
//            ).collectLatest { result ->
//                when (result) {
//                    is Resource.Error -> {
//                        _movieState.update { it.copy(error = result.message) }
//                    }
//
//                    is Resource.Loading -> {
//                        _movieState.update { it.copy(isLoading = result.isLoading) }
//                    }
//
//                    is Resource.Success -> {
//                        result.data?.let { upcomingMovie ->
//                            _movieState.update {
//                                it.copy(
//                                    upcomingMovieList = _movieState.value.upcomingMovieList +
//                                            upcomingMovie.shuffled(),
//                                    upcomingMoviePage = _movieState.value.upcomingMoviePage + 1
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }


    private fun getTrendingMovieList() {
        viewModelScope.launch {
            repository.getTrendingMovieList(
                type = Constant.ALL,
                time = Constant.TIME,
                page = 1,
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _movieState.update { it.copy(error = result.message, isLoading = false) }
                    }

                    is Resource.Loading -> {
                        _movieState.update { it.copy(isLoading = true) }

                    }

                    is Resource.Success -> {
                        result.data?.let { media ->
                            _movieState.update {
                                it.copy(
                                    trendingMovieList = media.shuffled(),
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
            }
        }
    }


    private fun getTopRatedSeries() {
        viewModelScope.launch {
            repository.getTopRatedSeries(
                type = Constant.SERIES_TYPE,
                category = Constant.TOP_RATED,
                page = 1
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _movieState.update { it.copy(error = result.message, isLoading = false) }
                    }

                    is Resource.Loading -> {
                        _movieState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        result.data?.let { media ->
                            _movieState.update { it.copy(topRatedSeriesList = media.shuffled()) }
                        }
                    }
                }
            }
        }
    }


}


















