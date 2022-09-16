package com.wipro.repository

import com.wipro.model.MoviesDetails
import com.wipro.networking.MoviesApiService
import com.wipro.networking.Resource
import com.wipro.networking.ResponseHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MovieDetailsRepositoryImpl(
    private val apiService: MoviesApiService,
    private val responseHandler: ResponseHandler
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): Flow<MoviesDetails> =
        flow { emit(apiService.getMoviesDetails(movieId)) }
//        return try {
//            responseHandler.handleSuccess(apiService.getMoviesDetails(movieId))
//        } catch (exception: Exception) {
//            responseHandler.handleException(exception)
//        }

}