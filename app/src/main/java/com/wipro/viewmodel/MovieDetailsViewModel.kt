package com.wipro.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wipro.model.MoviesDetails
import com.wipro.networking.Resource
import com.wipro.networking.Status
import com.wipro.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieDetailsRepository: MovieDetailsRepository) : ViewModel() {
    val movieDetais = MutableLiveData<Resource<MoviesDetails>>()
    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieDetailsRepository.getMovieDetails(movieId).catch { error->
                movieDetais.postValue(Resource(Status.ERROR,null,error.message))
            }.collect {
                movieDetais.postValue(Resource(Status.SUCCESS,it,null))
            }
        }
    }

}