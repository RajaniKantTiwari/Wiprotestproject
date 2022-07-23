package com.wipro.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.wipro.R
import com.wipro.db.dao.MovieListDao
import com.wipro.db.entity.TvShowEntity
import com.wipro.model.MovieList
import com.wipro.model.TvShow
import com.wipro.networking.Resource
import com.wipro.networking.Status
import com.wipro.util.Constant
import com.wipro.util.CoroutineScopeProvider
import com.wipro.util.NetworkUtils
import kotlinx.coroutines.launch

class MovieDataSource(
    private val moviesRepository: MoviesRepository,
    private val movieListDao: MovieListDao,
    private val context: Context
) :
    PageKeyedDataSource<Int, TvShow>() {
    private val status = MutableLiveData<Status>()
    private val firstPage = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShow>
    ) {
        loadInitialPage(callback)
    }

    private fun loadInitialPage(callback: LoadInitialCallback<Int, TvShow>) {
        status.postValue(Status.LOADING)
        CoroutineScopeProvider.coroutineScopeIo().launch {
            val moviesList = getMovieList(firstPage, false)
            if (moviesList.status == Status.SUCCESS) {
                moviesList.data?.tvShows?.let {
                    saveMovieListInDb(it)
                    callback.onResult(it, null, firstPage + 1)
                    status.postValue(Status.SUCCESS)
                }
            } else {
                status.postValue(Status.ERROR)
            }
        }
    }

    private suspend fun saveMovieListInDb(tvShowList: List<TvShow>) {
        movieListDao.insertMovies(tvShowList.map {
            TvShowEntity(
                it.id,
                it.country,
                it.endDate,
                it.imageUrl,
                it.name,
                it.network,
                it.permalink,
                it.startDate,
                it.status
            )
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        loadPage(params, callback)
        Log.e("LoadingStart", "loadBefore")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        loadPage(params, callback, false)
        Log.e("LoadingStart", "loadAfter")
    }

    private fun loadPage(
        params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>,
        before: Boolean = true
    ) {
        status.postValue(Status.LOADING)
        CoroutineScopeProvider.coroutineScopeIo().launch {
            val moviesList = getMovieList(params.key, before)
            if (moviesList.status == Status.SUCCESS) {
                moviesList.data?.tvShows?.let {
                    if (before) {
                        callback.onResult(it, if (params.key > 1) params.key - 1 else null)
                    } else {
                        callback.onResult(
                            it,
                            if (moviesList.data.pages > moviesList.data.page) params.key + 1 else null
                        )
                    }
                }
                status.postValue(Status.SUCCESS)
            } else {
                status.postValue(Status.ERROR)
            }
        }
    }

    private suspend fun getMovieList(pageNumber: Int, before: Boolean) =
        if (NetworkUtils.isNetworkAvailable(context)) {
            moviesRepository.getMoviesList(pageNumber)
        } else {
            getMovieListFromDb(pageNumber, before)
        }

    private suspend fun getMovieListFromDb(pageNumber: Int, before: Boolean): Resource<MovieList> {
        val data = movieListDao.getMoviesList(Constant.pageSize, (pageNumber-1)*Constant.pageSize)
        Log.e("DataSizeAre", "" + data.size + " " + pageNumber + " " + (pageNumber-1)*Constant.pageSize)
        val tvShowList = data.map {
            TvShow(
                it.id,
                it.country,
                it.endDate,
                it.imageUrl,
                it.name,
                it.network,
                it.permalink,
                it.startDate,
                it.status
            )
        }
        val pages = if (before) pageNumber - 1 else pageNumber + 1
        val movieList = MovieList(pageNumber, pages, "", tvShowList)
        if (data.isNotEmpty()) {
            return Resource(Status.SUCCESS, movieList, null)
        }
        return Resource(Status.ERROR, movieList, context.getString(R.string.something_went_wrong))
    }

    fun getNetworkState(): MutableLiveData<Status> {
        return status
    }

}







