package com.wipro.repository

import com.wipro.model.MoviesDetails
import com.wipro.networking.MoviesApiService
import com.wipro.networking.Resource
import com.wipro.networking.ResponseHandler
import java.lang.Exception

class MovieDetailsRepositoryImpl(
    private val apiService: MoviesApiService,
    private val responseHandler: ResponseHandler
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): Resource<MoviesDetails> {
        return try {
            responseHandler.handleSuccess(apiService.getMoviesDetails(movieId))
        } catch (exception: Exception) {
            responseHandler.handleException(exception)
        }
    }
}