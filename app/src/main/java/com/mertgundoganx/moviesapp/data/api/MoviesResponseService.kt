package com.mertgundoganx.moviesapp.data.api

import com.mertgundoganx.moviesapp.data.model.MoviesResponse
import com.mertgundoganx.moviesapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesResponseService {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    companion object {
        val service: MoviesResponseService by lazy {
            RetrofitBuilder.retrofitService(Constants.API_THE_MOVIE_DB_URL)
                .create(MoviesResponseService::class.java)
        }
    }
}