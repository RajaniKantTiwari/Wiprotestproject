package com.wipro.networking

import com.wipro.model.MovieList
import com.wipro.model.MoviesDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET("most-popular")
    suspend fun getMovies(@Query("page") page: Int): MovieList

    @GET("show-details")
    suspend fun getMoviesDetails(@Query("q") tvShowId: Int): MoviesDetails
}