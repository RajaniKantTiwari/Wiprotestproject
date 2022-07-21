package com.wipro.repository

import com.wipro.model.MovieList
import com.wipro.networking.MoviesApiService
import com.wipro.networking.Resource
import com.wipro.networking.ResponseHandler

class MoviesRepositoryImpl(private val apiService: MoviesApiService,private val responseHandler: ResponseHandler) : MoviesRepository {
    override suspend fun getMoviesList(page : Int): Resource<MovieList> {
        return try {
            responseHandler.handleSuccess(apiService.getMovies(page))
        } catch (exception: Exception) {
            responseHandler.handleException(exception)
        }
    }
}