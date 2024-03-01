package com.edu.filmku.ui.main.saved

import androidx.fragment.app.viewModels
import com.edu.filmku.core.BaseFragment
import com.edu.filmku.databinding.FragmentSavedBinding
import com.edu.filmku.domain.model.DetailMovieModel
import com.edu.filmku.ui.adapter.FavoriteAdapter
import com.edu.filmku.ui.detail.DetailActivity
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