package com.example.movieappcompose.domain.repository

import com.example.movieappcompose.domain.model.Movie
import com.example.movieappcompose.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovieListByCategory(
         category: String,
         page: Int,
         shouldFetchFromRemote: Boolean
    ): Flow<Resource<List<Movie>>>


    suspend fun getMovieListById(
        id: Int
    ): Flow<Resource<Movie>>

}