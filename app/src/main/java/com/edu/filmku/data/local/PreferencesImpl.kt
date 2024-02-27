package com.edu.filmku.data.local

import com.edu.filmku.domain.local.Preferences

/**
 *
 * Created by Lukmanul Hakim on  27/02/24
 * devs.lukman@gmail.com
 */
class PreferencesImpl(
    private val preferencesHelper: PreferencesHelper
) : Preferences {
    override val token: String?
        get() = preferencesHelper.getStringData(PREF_ANONYMOUS_TOKEN)

    override fun saveToken(token: String?) {
        preferencesHelper.saveDataString(PREF_ANONYMOUS_TOKEN, token)
    }

    override fun clearData() {
        preferencesHelper.delete(PREF_ANONYMOUS_TOKEN)
    }


    companion object {
        private const val PREF_ANONYMOUS_TOKEN = "KEY_PREF_TOKEN"
    }
}