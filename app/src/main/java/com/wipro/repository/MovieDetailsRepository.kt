package com.wipro.repository

import com.wipro.model.MoviesDetails
import com.wipro.networking.Resource

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId : Int ) : Resource<MoviesDetails>
}