package com.wipro

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wipro.adapter.MoviesListAdapter
import com.wipro.databinding.ActivityMoviesListBinding
import com.wipro.networking.Status
import com.wipro.util.NetworkUtils
import com.wipro.viewmodel.MoviesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesListBinding
    private val moviesListViewModel: MoviesListViewModel by viewModel()
    private lateinit var moviesListAdapter : MoviesListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesListBinding.inflate(layoutInflater)
        moviesListAdapter = MoviesListAdapter(this)
        setContentView(binding.root)
        initializeRecyclerView(binding)
        submitDataIntoAdapter(binding)
        if(!NetworkUtils.isNetworkAvailable(this)){
            Toast.makeText(this,getString(R.string.you_are_offline),Toast.LENGTH_LONG).show()
        }
    }

    private fun submitDataIntoAdapter(binding: ActivityMoviesListBinding) {
        moviesListViewModel.itemPageList.observe(this, {
            moviesListAdapter.submitList(it)
            binding.progressBar.visibility = View.GONE
        })
        moviesListViewModel.networkState.observe(this, {
            if (it == Status.LOADING) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                if(it == Status.ERROR) {
                    Toast.makeText(this,getString(R.string.something_went_wrong),Toast.LENGTH_LONG).show()
                }
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun initializeRecyclerView(binding: ActivityMoviesListBinding) {
        binding.rvMoviesList.adapter = moviesListAdapter
        binding.rvMoviesList.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }
}