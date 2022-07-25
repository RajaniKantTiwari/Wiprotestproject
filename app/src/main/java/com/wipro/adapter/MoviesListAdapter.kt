package com.wipro.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedListAdapter
import com.wipro.MoviesDetailsActivity
import com.wipro.databinding.ItemMoviesBinding
import com.wipro.model.TvShow
import com.wipro.util.Constant
import com.wipro.util.DiffUtilCallback
import com.wipro.viewholder.MoviesViewHolder

class MoviesListAdapter(private val activity: AppCompatActivity) : PagedListAdapter<TvShow, MoviesViewHolder>(DiffUtilCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
      return  MoviesViewHolder(ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.itemView.setOnClickListener { _ ->
                val intent = Intent(activity,MoviesDetailsActivity::class.java)
                intent.putExtra(Constant.ShowId, it.id)
                activity.startActivity(intent)
            }
            holder.bind(it)
        }
    }
}