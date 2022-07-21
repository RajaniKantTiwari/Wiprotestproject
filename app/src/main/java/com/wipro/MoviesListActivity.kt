package com.wipro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wipro.databinding.ActivityMoviesListBinding
import com.wipro.viewmodel.MoviesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListActivity : AppCompatActivity() {
    private val moviesListViewModel : MoviesListViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMoviesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        moviesListViewModel.itemPageList.observe(this,{
          binding.progressBar.visibility = View.VISIBLE

        })
    }
}