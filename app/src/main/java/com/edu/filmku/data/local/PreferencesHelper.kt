package com.edu.filmku.data.local

import android.content.Context

/**
 *
 * Created by Lukmanul Hakim on  27/02/24
 * devs.lukman@gmail.com
 */
class PreferencesHelper(context: Context) {

    private val preferences by lazy {
        context.getSharedPreferences("Cache.MyCinema", Context.MODE_PRIVATE)
    }

    fun saveDataString(key: String, data: String?) {
        preferences.edit().apply {
            putString(key, data)
            apply()
        }
    }

    fun delete(key : List<String>) {
        key.forEach { delete(it) }
    }
    fun delete(key: String) {
        preferences.edit().apply {
            remove(key)
            apply()
        }
    }

    fun getStringData(key : String) : String? {
        return preferences.getString(key, null)
    }
}