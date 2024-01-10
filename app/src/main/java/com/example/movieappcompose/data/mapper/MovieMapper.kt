package com.example.movieappcompose.data.mapper

import com.example.movieappcompose.data.local.MovieEntity
import com.example.movieappcompose.data.remote.MediaDto
import com.example.movieappcompose.data.remote.MovieDto
import com.example.movieappcompose.domain.model.Media
import com.example.movieappcompose.domain.model.Movie


fun MovieDto.toMovieEntity(
    category: String
): MovieEntity {
    return MovieEntity(
        adult = adult ?: false,
        backdrop_path = backdrop_path ?: "",
        genre_ids = try {
            genre_ids?.joinToString(",") ?: "-1, -2"
        } catch (e: Exception) {
            "-1,-2"
        },
        id = id ?: -1,
        original_language = original_language ?: "",
        original_title = original_title ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        poster_path = poster_path ?: "",
        release_date = release_date ?: "",
        title = title ?: "",
        video = video ?: false,
        vote_average = vote_average ?: 0.0,
        vote_count = vote_count ?: 0,
        category = category
    )

}

fun MovieEntity.toMovie(
    category: String
): Movie {
    return Movie(
        adult = adult,
        backdrop_path = backdrop_path,
        genre_ids = try {
            genre_ids.split(",").map { it.toInt() }
        } catch (e: Exception) {
            listOf(-1, -2)
        },
        id = id,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count,
        category = category
    )

}

fun MediaDto.toMedia(
    type: String,
    category: String,
): Media {
    return Media(
        backdropPath = backdrop_path ?: "",
        originalLanguage = original_language ?: "",
        overview = overview ?: "",
        posterPath = poster_path ?: "",
        releaseDate = release_date ?: "",
        title = title ?: name ?: "",
        voteAverage = vote_average ?: 0.0,
        popularity = popularity ?: 0.0,
        voteCount = vote_count ?: 0,
        genreIds = genre_ids ?: emptyList(),
        id = id ?: 1,
        adult = adult ?: false,
        mediaType = type,
        category = category,
        originCountry = origin_country ?: emptyList(),
        originalTitle = original_title ?: original_name ?: "",
        runtime = null,
        status = null,
        tagline = null,
        videos = videos,
        similarMediaList = similarMediaList ?: emptyList()
    )
}






