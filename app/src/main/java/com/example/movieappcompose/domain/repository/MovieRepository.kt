package com.example.movieappcompose.domain.repository

import com.example.movieappcompose.data.local.MovieEntity
import com.example.movieappcompose.domain.model.Media
import com.example.movieappcompose.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {


    suspend fun getMovieList(
        category: String,
        page: Int
    ): Flow<Resource<List<Media>>>



    suspend fun upsertMovie(movieEntity: MovieEntity)

    suspend fun deleteMovie(movieEntity: MovieEntity)

     fun getFavoriteMovie(): Flow<Resource<List<Media>>>


    suspend fun getTrendingMovieList(
        type: String,
        time: String,
        page: Int,
    ): Flow<Resource<List<Media>>>

    suspend fun getTopRatedSeries(
        type: String,
        category: String,
        page: Int
    ): Flow<Resource<List<Media>>>

    suspend fun getSearchList(
        query: String,
        page: Int
    ): Flow<Resource<List<Media>>>


    suspend fun getSimilarList(
        type: String,
        id: Int,
        page: Int
    ): Flow<Resource<List<Media>>>


}



