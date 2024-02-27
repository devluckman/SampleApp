package com.edu.filmku.ui.main.saved

import com.edu.filmku.core.BaseFragment
import com.edu.filmku.databinding.FragmentSavedBinding


class SavedFragment : BaseFragment<FragmentSavedBinding>(
    FragmentSavedBinding::inflate
) {
    override fun onViewReady() {

    }


    companion object {
        fun newInstance() : SavedFragment {
            return SavedFragment()
        }
    }
}