package com.wipro.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.wipro.model.TvShow

class MoviesDataSourceFactory(private val itemDataSource: MovieDataSource) : DataSource.Factory<Int, TvShow>() {
    val itemLiveDataSource = MutableLiveData<MovieDataSource>()
    override fun create(): DataSource<Int, TvShow> {
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }
}