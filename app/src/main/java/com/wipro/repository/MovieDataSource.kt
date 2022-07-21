package com.wipro.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.wipro.model.TvShow
import com.wipro.networking.Status
import com.wipro.util.CoroutineScopeProvider
import kotlinx.coroutines.launch

class MovieDataSource(private val moviesRepository: MoviesRepository) :
    PageKeyedDataSource<Int, TvShow>() {

    //we will start from the first page which is 1
    private val FIRST_PAGE = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShow>
    ) {
        CoroutineScopeProvider.coroutineScopeIo().launch {
            val moviesList = moviesRepository.getMoviesList(FIRST_PAGE)
            if (moviesList.status == Status.SUCCESS) {
                moviesList.data?.tv_shows?.let {
                    callback.onResult(it, null, FIRST_PAGE + 1)
                    Log.e("IHaveCalled",""+it.size)
                }
            } else {
                Log.e("ErrorWhenCalling",""+moviesList.message)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        CoroutineScopeProvider.coroutineScopeIo().launch {
            val moviesList = moviesRepository.getMoviesList(params.key)
            if (moviesList.status == Status.SUCCESS) {
                moviesList.data?.tv_shows?.let { callback.onResult(it, if (params.key > 1) params.key - 1 else null) }
            } else {
                Log.e("ErrorWhenCalling",""+moviesList.message)
            }
        }
    }



    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        CoroutineScopeProvider.coroutineScopeIo().launch {
            val moviesList = moviesRepository.getMoviesList(params.key)
            if (moviesList.status == Status.SUCCESS) {
                moviesList.data?.tv_shows?.let { callback.onResult(it, if (moviesList.data.pages > moviesList.data.page) params.key + 1 else null) }
            } else {
                Log.e("ErrorWhenCalling",""+moviesList.message)
            }
        }
    }

//    private fun getMoviesList(
//        callback: LoadCallback<Int, TvShow>, currentPage : Int,
//        adjacentKey: Int?
//    ) {
//        CoroutineScopeProvider.coroutineScopeIo().launch {
//            val moviesList = moviesRepository.getMoviesList(currentPage)
//            if (moviesList.status == Status.SUCCESS) {
//                moviesList.data?.tv_shows?.let { callback.onResult(it, adjacentKey) }
//            } else {
//                Log.e("ErrorWhenCalling",""+moviesList.message)
//            }
//        }
//    }

}







