package com.edu.filmku.ui.detail

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.edu.filmku.core.BaseActivity
import com.edu.filmku.databinding.ActivityDetailBinding
import com.edu.filmku.ui.adapter.CastAdapter
import com.edu.filmku.ui.adapter.GenreAdapter
import com.edu.filmku.ui.main.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(
    ActivityDetailBinding::inflate
) {
    private val viewModel: DetailVM by viewModels()
    private val genreAdapter by lazy { GenreAdapter() }
    private val castAdapter by lazy { CastAdapter() }
    override fun onViewReady() {
        binding.rvCastMovie.adapter = castAdapter
        binding.rvGenres.adapter = genreAdapter
        viewModel.detailDataMovie.observe(this) {
            binding.apply {
                tvMovieName.text = it.title
                tvImbd.text = it.rating
                tvDuration.text = it.duration
                tvDescription.text = it.description
                Glide.with(ivPoster)
                    .load(it.poster)
                    .into(ivPoster)
                genreAdapter.submit(it.genre)
            }
        }

        viewModel.castMovieData.observe(this) {
            castAdapter.submit(it)
        }

        val id = intent.getStringExtra(KEY_ID_MOVIE)
        viewModel.getDetailMovie(id)
        viewModel.getCastMovie(id)
    }


    companion object {
        const val KEY_ID_MOVIE = "KEY_ID_MOVIE"
        fun newInstance(context: Context?, id: String) {
            context?.let {
                val intent = Intent(it, DetailActivity::class.java)
                intent.putExtra(KEY_ID_MOVIE, id)
                it.startActivity(intent)
            }
        }
    }
}