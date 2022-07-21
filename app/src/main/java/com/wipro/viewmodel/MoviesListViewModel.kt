package com.wipro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wipro.model.TvShow
import com.wipro.repository.MoviesDataSourceFactory

class MoviesListViewModel(movieDataSourceFactory : MoviesDataSourceFactory) : ViewModel() {
    var itemPageList: LiveData<PagedList<TvShow>> =
        LivePagedListBuilder(movieDataSourceFactory, PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()).build()
    //   fun getMoviesList() {
//       viewModelScope.launch(Dispatchers.IO) {
//           val moviesList=moviesRepository.getMoviesList(1).data
//           Log.e("TVShowList1234",""+moviesList?.tv_shows?.size)
//       }
//   }
}