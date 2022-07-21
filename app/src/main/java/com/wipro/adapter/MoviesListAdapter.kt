package com.wipro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.wipro.databinding.ItemMoviesBinding
import com.wipro.model.TvShow
import com.wipro.util.DiffUtilCallback
import com.wipro.viewholder.MoviesViewHolder

class MoviesListAdapter : PagedListAdapter<TvShow, MoviesViewHolder>(DiffUtilCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
      return  MoviesViewHolder(ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}