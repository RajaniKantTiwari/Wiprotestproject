package com.wipro.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wipro.model.MoviesDetails
import com.wipro.networking.Resource
import com.wipro.repository.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieDetailsRepository: MovieDetailsRepository) : ViewModel() {
    val movieDetais = MutableLiveData<Resource<MoviesDetails>>()
    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieDetails: Resource<MoviesDetails> =movieDetailsRepository.getMovieDetails(movieId)
            movieDetais.postValue(movieDetails)
            Log.e("MovieDetailsAre",""+movieDetails.data.toString() + " "+movieDetails.status)

        }
    }

}