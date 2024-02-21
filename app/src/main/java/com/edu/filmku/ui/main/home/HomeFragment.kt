package com.edu.filmku.ui.main.home

import com.edu.filmku.core.BaseFragment
import com.edu.filmku.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    override fun onViewReady() {

    }

    companion object {
        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }
}