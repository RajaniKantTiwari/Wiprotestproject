package com.wipro.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wipro.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesListViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
   fun getMoviesList() {
       viewModelScope.launch(Dispatchers.IO) {
           val moviesList=moviesRepository.getMoviesList(1).data
           Log.e("TVShowList1234",""+moviesList?.tv_shows?.size)
       }
   }
}