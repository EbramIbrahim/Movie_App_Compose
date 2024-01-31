package com.example.movieappcompose.data.mapper

import com.example.movieappcompose.data.local.MovieEntity
import com.example.movieappcompose.data.remote.MediaDto
import com.example.movieappcompose.data.remote.MovieDto
import com.example.movieappcompose.domain.model.Media
import com.example.movieappcompose.domain.model.Movie

// from remote to local
fun MediaDto.toMovieEntity(
    category: String
): MovieEntity {
    return MovieEntity(
        backdropPath = backdrop_path ?: "",
        originalLanguage = original_language ?: "",
        overview = overview ?: "",
        posterPath = poster_path ?: "",
        releaseDate = release_date ?: "-1,-2",
        title = title ?: name ?: "",
        originalName = original_name ?: "",
        voteAverage = vote_average ?: 0.0,
        popularity = popularity ?: 0.0,
        voteCount = vote_count ?: 0,
        genreIds = try {
            genre_ids?.joinToString(",") ?: "-1,-2"
        } catch (e: Exception) {
            "-1,-2"
        },
        id = id ?: 1,
        adult = adult ?: false,
        mediaType = media_type ?: "",
        category = category,
        originCountry = try {
            origin_country?.joinToString(",") ?: "-1,-2"
        } catch (e: Exception) {
            "-1,-2"
        },
        originalTitle = original_title ?: original_name ?: "",
        videos = try {
            videos?.joinToString(",") ?: "-1,-2"
        } catch (e: Exception) {
            "-1,-2"
        },
        similarMediaList = try {
            similarMediaList?.joinToString(",") ?: "-1,-2"
        } catch (e: Exception) {
            "-1,-2"
        },
        firstAirDate = first_air_date ?: "",
        video = video ?: false,

        status = "",
        runtime = 0,
        tagline = "",
        isWatched = false,
        isFavorite = false
    )
}

// from local to Model
fun MovieEntity.toMedia(): Media {
    return Media(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = try {
            genreIds.split(",").map { it.toInt() }
        } catch (e: Exception) {
            listOf(-1, -2)
        },
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        videos = try {
            videos.split(",")
        } catch (e: Exception) {
            listOf("")
        },
        voteAverage = voteAverage,
        voteCount = voteCount,
        category = category,
        similarMediaList = try {
            similarMediaList.split(",").map { it.toInt() }
        } catch (e: Exception) {
            listOf(-1, -2)
        },
        runtime = runtime ,
        status = status,
        mediaType = mediaType,
        originCountry = try {
            originCountry.split(",")
        } catch (e: Exception) {
            listOf("-1", "-2")
        },
        tagline = tagline,
        isWatched = false,
        isFavorite = false
    )

}

//from remote to Model
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
        similarMediaList = similarMediaList ?: emptyList(),
        isFavorite = false,
        isWatched = false,
    )
}

fun Media.toMovieEntity(
    isFavorite: Boolean,
    isWatched: Boolean,
): MovieEntity {
    return MovieEntity(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = try {
            genreIds.joinToString(",")
        } catch (e: Exception) {
            "-1, -2"
        },
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        videos = try {
            videos?.joinToString(",")
        } catch (e: Exception) {
            ""
        }.toString(),
        voteAverage = voteAverage,
        voteCount = voteCount,
        category = category,
        similarMediaList = try {
            similarMediaList.joinToString(",")
        } catch (e: Exception) {
            "-1, -2"
        },
        runtime = runtime ?: -1 ,
        status = status ?: "-1",
        mediaType = mediaType,
        originCountry = try {
            originCountry.joinToString(",")
        } catch (e: Exception) {
            "-1 -2"
        },
        tagline = tagline ?: "-1",
        isWatched = isWatched,
        isFavorite = isFavorite,
        firstAirDate = ",",
        originalName = "",
        video = false
    )

}





