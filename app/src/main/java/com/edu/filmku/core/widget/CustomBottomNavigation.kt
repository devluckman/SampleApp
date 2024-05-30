package com.edu.filmku.core.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import com.edu.filmku.R
import com.edu.filmku.core.base.findIdByLazy

/**
 *
 * Created by Lukmanul Hakim on  23/02/24
 * devs.lukman@gmail.com
 */
class CustomBottomNavigation(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {

    private val ivMenuHome: AppCompatImageView by findIdByLazy(R.id.iv_menu_home)
    private val ivMenuBookmark: AppCompatImageView by findIdByLazy(R.id.iv_menu_bookmark)
    private var callbackNav: CallbackNav? = null
    private var _currentPosition : MenuNavigation = MenuNavigation.HOME
    val currentPosition : MenuNavigation get() = _currentPosition
    init {
        inflate(context, R.layout.custom_navigation_bottom, this)
        ivMenuHome.setOnClickListener {
            setNavigationMenu(MenuNavigation.HOME)
        }
        ivMenuBookmark.setOnClickListener {
            setNavigationMenu(MenuNavigation.BOOKMARK)
        }
    }

    fun setNavigationMenu(menu: MenuNavigation) {
        when (menu) {
            MenuNavigation.HOME -> {
                ivMenuHome.setImageResource(R.drawable.ic_home_on)
                ivMenuBookmark.setImageResource(R.drawable.ic_bookmark_off)
            }

            MenuNavigation.BOOKMARK -> {
                ivMenuHome.setImageResource(R.drawable.ic_home_off)
                ivMenuBookmark.setImageResource(R.drawable.ic_bookmark_on)
            }
        }
        _currentPosition = menu
        callbackNav?.onPagePosition(menu)
    }

    enum class MenuNavigation {
        HOME, BOOKMARK
    }

    interface CallbackNav {
        fun onPagePosition(menu: MenuNavigation)
    }

    fun listerPositionNavigation(callback: CallbackNav) {
        callbackNav = callback
        setNavigationMenu(MenuNavigation.HOME)
    }
}