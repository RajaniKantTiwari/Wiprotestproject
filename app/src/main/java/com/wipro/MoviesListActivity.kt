package com.wipro

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wipro.adapter.MoviesListAdapter
import com.wipro.databinding.ActivityMoviesListBinding
import com.wipro.viewmodel.MoviesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListActivity : AppCompatActivity() {
    private val moviesListViewModel: MoviesListViewModel by viewModel()
    private val moviesListAdapter = MoviesListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMoviesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeRecyclerView(binding)
        submitDataIntoAdapter(binding)
    }

    private fun submitDataIntoAdapter(binding: ActivityMoviesListBinding) {
        moviesListViewModel.itemPageList.observe(this, {
            binding.progressBar.visibility = View.VISIBLE
            moviesListAdapter.submitList(it)
            binding.progressBar.visibility = View.GONE
        })
    }

    private fun initializeRecyclerView(binding: ActivityMoviesListBinding) {
        binding.rvMoviesList.adapter = moviesListAdapter
        binding.rvMoviesList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }
}