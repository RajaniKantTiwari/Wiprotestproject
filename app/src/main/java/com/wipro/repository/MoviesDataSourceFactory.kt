package com.wipro.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.wipro.model.MovieList
import com.wipro.model.TvShow

class MoviesDataSourceFactory(private val itemDataSource: MovieDataSource) : DataSource.Factory<Int, TvShow>() {
    val itemLiveDataSoure = MutableLiveData<PageKeyedDataSource<Int, TvShow>>()
    override fun create(): DataSource<Int, TvShow> {
        itemLiveDataSoure.postValue(itemDataSource)
        return itemDataSource
    }
}