package com.edu.filmku.presentation.main

import com.edu.filmku.core.base.BaseActivity
import com.edu.filmku.databinding.ActivityMainBinding
import com.edu.filmku.presentation.main.home.HomeFragment
import com.edu.filmku.presentation.main.saved.SavedFragment
import com.edu.filmku.core.widget.CustomBottomNavigation
import com.edu.filmku.core.widget.CustomBottomNavigation.MenuNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {


    override fun onViewReady() {
        binding.apply {
            bottomNavigation.listerPositionNavigation(
                callback = object : CustomBottomNavigation.CallbackNav {
                    override fun onPagePosition(menu: MenuNavigation) {
                        val fragment = when (menu) {
                            MenuNavigation.HOME -> HomeFragment.newInstance()
                            MenuNavigation.BOOKMARK -> SavedFragment.newInstance()
                        }
                        inflateFragment(container.id, fragment)
                    }
                }
            )
        }
    }

    override fun onBackPressEvent() {
        binding.apply {
            if (bottomNavigation.currentPosition == MenuNavigation.HOME) {
                finish()
            }else{
                bottomNavigation.setNavigationMenu(MenuNavigation.HOME)
            }
        }
    }
}