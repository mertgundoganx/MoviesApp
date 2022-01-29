package com.mertgundoganx.moviesapp.di

import com.mertgundoganx.moviesapp.data.api.MoviesResponseService
import com.mertgundoganx.moviesapp.data.repository.MoviesResponseRepository
import com.mertgundoganx.moviesapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.API_THE_MOVIE_DB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesResponseService(retrofit: Retrofit): MoviesResponseService {
        return retrofit.create(MoviesResponseService::class.java)
    }


    @Singleton
    @Provides
    fun provideMoviesResponseRepository(moviesResponseService: MoviesResponseService): MoviesResponseRepository {
        return MoviesResponseRepository(moviesResponseService)
    }

}