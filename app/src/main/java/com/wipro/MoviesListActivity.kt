package com.wipro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wipro.databinding.ActivityMoviesListBinding

class MoviesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMoviesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}