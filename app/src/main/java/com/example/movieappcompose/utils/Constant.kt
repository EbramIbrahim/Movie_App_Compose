package com.example.movieappcompose.utils

import androidx.datastore.preferences.core.booleanPreferencesKey

object Constant {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "7b8b48868f94b427e862e29fedb3f3ef"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    const val POPULAR = "popular"
    const val UPCOMING = "upcoming"
    const val TRENDING = "trending"
    const val TIME = "day"
    const val ALL = "all"
    const val trendingAllListScreen = "trendingAllListScreen"
    const val tvSeriesScreen = "tvSeriesScreen"
    const val TOP_RATED = "top_rated"
    const val SERIES_TYPE = "tv"
    const val DATA_STORE_NAME = "favorite_watched_movie"

    val readFavoriteMovie = booleanPreferencesKey(name = "favorite_movie")
    val readWatchedMovie = booleanPreferencesKey(name = "watched_movie")


}