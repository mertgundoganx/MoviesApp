package com.mertgundoganx.moviesapp.data.repository

import com.mertgundoganx.moviesapp.BuildConfig
import com.mertgundoganx.moviesapp.data.api.MoviesResponseService

class MoviesRepository(private val moviesResponseService: MoviesResponseService) {

    private val apiKey = BuildConfig.apiKey

    suspend fun getMovies(page: Int) = moviesResponseService.getMovies(apiKey, page)

}