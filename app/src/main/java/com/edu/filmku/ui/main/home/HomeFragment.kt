package com.edu.filmku.ui.main.home

import android.content.Intent
import androidx.fragment.app.viewModels
import com.edu.filmku.core.BaseFragment
import com.edu.filmku.databinding.FragmentHomeBinding
import com.edu.filmku.ui.adapter.NowPlayingAdapter
import com.edu.filmku.ui.adapter.PopularAdapter
import com.edu.filmku.ui.splash.LandingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels()
    private val adapterNowPlay by lazy { NowPlayingAdapter() }
    private val adapterPopular by lazy { PopularAdapter() }

    override fun onViewReady() {
        binding.rvNowPlaying.adapter = adapterNowPlay
        binding.rvPopular.adapter = adapterPopular
        binding.btnLogout.setOnClickListener {
            goToLogout()
        }
        viewModel.nowPlayingData.observe(this) {
            adapterNowPlay.submit(it.orEmpty())
        }

        viewModel.popularData.observe(this) {
            adapterPopular.submit(it.orEmpty())
        }
    }

    private fun goToLogout() {
        activity?.apply {
            viewModel.logout()
            startActivity(Intent(this, LandingActivity::class.java))
            finish()
        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}