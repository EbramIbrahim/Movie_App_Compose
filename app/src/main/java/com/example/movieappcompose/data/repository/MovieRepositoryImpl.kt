package com.example.movieappcompose.data.repository

import com.example.movieappcompose.data.local.MovieDatabase
import com.example.movieappcompose.data.mapper.toMedia
import com.example.movieappcompose.data.remote.MovieApi
import com.example.movieappcompose.domain.model.Media
import com.example.movieappcompose.domain.repository.MovieRepository
import com.example.movieappcompose.utils.Constant
import com.example.movieappcompose.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) : MovieRepository {


    override suspend fun getMovieList(
        category: String,
        page: Int
    ): Flow<Resource<List<Media>>> {
        return flow {

            emit(Resource.Loading(true))
            delay(2000L)

            val fetchPopularList = try {
                movieApi.getMovieList(category, page)
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage!!))
                return@flow
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage!!))
                return@flow
            }

            val media = fetchPopularList.results.map {
                it.toMedia(type = it.media_type ?: "", category = category)
            }

            emit(Resource.Success(media))

        }
    }


    override suspend fun getMovieListById(id: Int): Flow<Resource<Media>> {
        return channelFlow {

            withContext(Dispatchers.IO) {
                send(Resource.Loading(true))


                val movieDetails = movieDatabase.movieDao.getMoviesById(id)
                val movieEntity = movieDetails.toMedia(movieDetails.category)


                send(Resource.Success(movieEntity))

                send(Resource.Loading(false))
            }

        }
    }

    override suspend fun getTrendingMovieList(
        type: String,
        time: String,
        page: Int,
    ): Flow<Resource<List<Media>>> {
        return flow {

            emit(Resource.Loading(true))
            delay(1000L)


            val trendingMovieList = try {
                movieApi.getTrendingMovie(type, time, page)
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage!!))
                return@flow
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage!!))
                return@flow
            }


            trendingMovieList.let {
                val media = it.results.map { media ->
                    media.toMedia(media.media_type ?: "", Constant.TRENDING)
                }

                emit(
                    Resource.Success(media)
                )
                emit(Resource.Loading(false))
            }

        }
    }


    override suspend fun getTopRatedSeries(
        type: String,
        category: String,
        page: Int
    ): Flow<Resource<List<Media>>> {
        return flow {

            emit(Resource.Loading(true))
            delay(1000L)

            val topRatedSeries = try {
                movieApi.getTopRatedSeries(
                    type, category, page
                )
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage!!))
                return@flow
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage!!))
                return@flow
            }

            emit(
                Resource.Success(
                    topRatedSeries.results.map {
                        it.toMedia(type = it.media_type ?: "", category = category)
                    })
            )


        }
    }

    override suspend fun getSearchList(
        query: String,
        page: Int
    ): Flow<Resource<List<Media>>> {
        return flow {

            emit(Resource.Loading(true))
            delay(1000L)

            val remoteSearchList = try {
                movieApi.getSearchList(query, page)
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage!!))
                return@flow
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage!!))
                return@flow
            }

            emit(
                Resource.Success(
                    remoteSearchList.results.map {
                        it.toMedia(
                            type = it.media_type ?: "",
                            category = it.category ?: ""
                        )
                    }
                )
            )

        }
    }

    override suspend fun getSimilarList(
        type: String,
        id: Int,
        page: Int
    ): Flow<Resource<List<Media>>> {
        return flow {

            emit(Resource.Loading(true))


            val fetchSimilarList = try {
                movieApi.getSimilarList(
                    type, id, page
                )
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage!!))
                return@flow
            } catch (e: HttpException) {
                emit((Resource.Error(e.localizedMessage!!)))
                return@flow
            }



            val media = fetchSimilarList.body()?.results?.map {
                it.toMedia(it.media_type ?: "", category = it.category ?: "")
            }

            emit(Resource.Success(media))
        }
    }
}




