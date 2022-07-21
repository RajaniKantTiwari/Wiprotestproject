package com.wipro.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.wipro.databinding.ItemMoviesBinding
import com.wipro.model.TvShow


class MoviesViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(tvShow: TvShow) {
        binding.tvShow = tvShow
    }
}

