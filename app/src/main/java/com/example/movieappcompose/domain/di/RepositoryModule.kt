package com.example.movieappcompose.domain.di

import com.example.movieappcompose.data.repository.MovieRepositoryImpl
import com.example.movieappcompose.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun provideRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository


}










