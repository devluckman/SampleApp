package com.edu.filmku.ui.main.search

import com.edu.filmku.core.BaseFragment
import com.edu.filmku.databinding.FragmentSearchBinding


class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {
    override fun onViewReady() {

    }


    companion object {
        fun newInstance() : SearchFragment {
            return SearchFragment()
        }
    }
}