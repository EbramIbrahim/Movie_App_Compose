package com.example.movieappcompose.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface MovieDao {

    @Upsert
    suspend fun insertMovie(movieEntity: List<MovieEntity>)


    @Query("SELECT * FROM MOVIE_TABLE WHERE category = :category")
    fun getMoviesByCategory(category: String): List<MovieEntity>


    @Query("SELECT * FROM MOVIE_TABLE WHERE id = :id")
    fun getMoviesById(id: Int): MovieEntity
}