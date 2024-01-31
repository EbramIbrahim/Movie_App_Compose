package com.example.movieappcompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: MovieEntity)


    @Query("SELECT * FROM MOVIE_TABLE WHERE isWatched = 1")
    fun getWatchedMovies(): Flow<List<MovieEntity>>


    @Query("SELECT * FROM MOVIE_TABLE WHERE isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>
}