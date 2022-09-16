package com.wipro.repository

import com.wipro.model.MoviesDetails
import com.wipro.networking.Resource
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId : Int ) : Flow<MoviesDetails>
}