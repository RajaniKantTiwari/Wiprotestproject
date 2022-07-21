package com.wipro.repository

import com.wipro.model.MovieList
import com.wipro.networking.Resource

interface MoviesRepository {
    suspend fun getMoviesList(page: Int): Resource<MovieList>
}