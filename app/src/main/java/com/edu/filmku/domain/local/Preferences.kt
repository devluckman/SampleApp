package com.edu.filmku.domain.local

/**
 *
 * Created by Lukmanul Hakim on  27/02/24
 * devs.lukman@gmail.com
 */
interface Preferences {
    val token : String?
    fun saveToken(token : String?)

    fun clearData()
}