package com.edu.filmku.ui.main.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edu.filmku.R
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