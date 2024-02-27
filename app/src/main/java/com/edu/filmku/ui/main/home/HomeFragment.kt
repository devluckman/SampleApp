package com.edu.filmku.ui.main.home

import androidx.fragment.app.viewModels
import com.edu.filmku.core.BaseFragment
import com.edu.filmku.databinding.FragmentHomeBinding
import com.edu.filmku.ui.adapter.ShowingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels()
    private val adapterNowPlay by lazy { ShowingAdapter() }
    override fun onViewReady() {
        binding.rvNowPlaying.adapter = adapterNowPlay
        viewModel.nowPlayingData.observe(this) {
            adapterNowPlay.submit(it.orEmpty())
        }
    }

    companion object {
        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }
}