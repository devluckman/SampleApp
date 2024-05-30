package com.edu.filmku.presentation.main.saved

import android.view.View
import androidx.fragment.app.viewModels
import com.edu.filmku.core.base.BaseFragment
import com.edu.filmku.databinding.FragmentSavedBinding
import com.edu.filmku.domain.model.DetailMovieModel
import com.edu.filmku.presentation.adapter.FavoriteAdapter
import com.edu.filmku.presentation.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : BaseFragment<FragmentSavedBinding>(
    FragmentSavedBinding::inflate
) {

    private val viewModel: SavedViewModel by viewModels()
    private val adapterSaved by lazy { FavoriteAdapter(::openDetail) }

    override fun onViewReady() {
        binding.rvSaved.adapter = adapterSaved
        viewModel.favoriteData.observe(this) {
            adapterSaved.submit(it.orEmpty())
            stateView(it.isNotEmpty())

        }
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = false
                viewModel.getAllMovieFavorite()
            }
        }
    }

    private fun stateView(dataAvailable : Boolean) {
        binding.apply {
            rvSaved.visibility = if (dataAvailable) View.VISIBLE else View.GONE
            lnEmpty.visibility = if (dataAvailable) View.GONE else View.VISIBLE
        }
    }

    private fun openDetail(data : DetailMovieModel) {
        DetailActivity.newInstance(context, data.id)
    }

    companion object {
        fun newInstance() : SavedFragment {
            return SavedFragment()
        }
    }
}