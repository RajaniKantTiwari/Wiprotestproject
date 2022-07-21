package com.wipro.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wipro.databinding.ItemMoviesBinding
import com.wipro.model.TvShow
import com.wipro.util.UIUtils
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class MoviesViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(tvShow: TvShow) {
        binding.tvShow = tvShow
        Glide.with(binding.ivMovies.context)
            .load(tvShow.imageUrl)
            .apply(
                RequestOptions.bitmapTransform(
                    RoundedCorners(
                        UIUtils.dpToPx(
                            10,
                            binding.ivMovies.context
                        )
                    )
                )
            )
            .into(binding.ivMovies)
    }
}

