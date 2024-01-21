package com.example.movieappcompose.domain.repository

import com.example.movieappcompose.domain.model.Media
import com.example.movieappcompose.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {



    suspend fun getMovieListById(
        id: Int
    ): Flow<Resource<Media>>

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



