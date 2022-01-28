package com.mertgundoganx.moviesapp.data.repository

import com.mertgundoganx.moviesapp.BuildConfig
import com.mertgundoganx.moviesapp.data.api.MoviesService

class MoviesRepository(private val moviesService: MoviesService) {

    private val apiKey = BuildConfig.apiKey

    suspend fun getMovies() = moviesService.getMovies(apiKey)

}