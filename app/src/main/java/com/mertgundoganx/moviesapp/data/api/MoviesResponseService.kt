package com.mertgundoganx.moviesapp.data.api

import com.mertgundoganx.moviesapp.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesResponseService {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

}