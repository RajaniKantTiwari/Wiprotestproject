package com.wipro

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.wipro.databinding.ActivityMoviesDetailsBinding
import com.wipro.model.TvShowDetails
import com.wipro.networking.Status
import com.wipro.util.Constant
import com.wipro.util.UIUtils
import com.wipro.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesDetailsActivity : AppCompatActivity() {
    private val viewModel: MovieDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMoviesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.visibility = View.VISIBLE
        Log.e("ParcellableExtraString", "" + intent.getIntExtra(Constant.ShowId, 0))
        viewModel.getMovieDetails(intent.getIntExtra(Constant.ShowId, 0))
        viewModel.movieDetais.observe(this) {
            if (it.status == Status.SUCCESS) {
                it.data?.tvShow?.let { tvShow ->
                    binding.tvShowDetails = tvShow
                    setImage(tvShow,binding)
                    binding.movieDetails.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
            } else {
                binding.movieDetails.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun setImage(tvShow: TvShowDetails, binding: ActivityMoviesDetailsBinding) {
        UIUtils.setImageIntoView(this, tvShow.image_path, binding.ivPoster)
        UIUtils.setImageIntoView(this, tvShow.image_thumbnail_path, binding.imageTVShow,10)
    }
}