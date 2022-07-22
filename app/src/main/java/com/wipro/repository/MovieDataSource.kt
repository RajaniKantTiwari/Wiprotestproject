package com.wipro.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.wipro.model.TvShow
import com.wipro.networking.Status
import com.wipro.util.CoroutineScopeProvider
import kotlinx.coroutines.launch

class MovieDataSource(private val moviesRepository: MoviesRepository) :
    PageKeyedDataSource<Int, TvShow>() {
    private val status = MutableLiveData<Status>()
    private val firstPage = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShow>
    ) {
        status.postValue(Status.LOADING)
        CoroutineScopeProvider.coroutineScopeIo().launch {
            val moviesList = moviesRepository.getMoviesList(firstPage)
            if (moviesList.status == Status.SUCCESS) {
                moviesList.data?.tv_shows?.let {
                    callback.onResult(it, null, firstPage + 1)
                    status.postValue(Status.SUCCESS)
                }

            } else {
                status.postValue(Status.ERROR)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        status.postValue(Status.LOADING)
        CoroutineScopeProvider.coroutineScopeIo().launch {
            val moviesList = moviesRepository.getMoviesList(params.key)
            if (moviesList.status == Status.SUCCESS) {
                moviesList.data?.tv_shows?.let { callback.onResult(it, if (params.key > 1) params.key - 1 else null) }
                status.postValue(Status.SUCCESS)
            } else {
                status.postValue(Status.ERROR)
            }
        }
    }



    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        status.postValue(Status.LOADING)
        CoroutineScopeProvider.coroutineScopeIo().launch {
            val moviesList = moviesRepository.getMoviesList(params.key)
            if (moviesList.status == Status.SUCCESS) {
                moviesList.data?.tv_shows?.let { callback.onResult(it, if (moviesList.data.pages > moviesList.data.page) params.key + 1 else null) }
                status.postValue(Status.SUCCESS)
            } else {
                status.postValue(Status.ERROR)
            }
        }
    }
    fun getNetworkState(): MutableLiveData<Status> {
        return status
    }

}







