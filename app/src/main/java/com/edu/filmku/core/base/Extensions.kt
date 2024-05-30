package com.edu.filmku.core.base

import android.view.View
import androidx.annotation.IdRes

/**
 *
 * Created by Lukmanul Hakim on  16/02/24
 * devs.lukman@gmail.com
 */


fun <T : View> View.findIdByLazy(@IdRes id: Int): Lazy<T> {
    return lazy { findViewById(id) }
}