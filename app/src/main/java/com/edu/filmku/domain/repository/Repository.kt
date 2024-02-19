package com.edu.filmku.domain.repository

import com.edu.filmku.data.network.Resource
import com.edu.filmku.domain.request.RequestLogin
import com.edu.filmku.domain.request.RequestRegister
import kotlinx.coroutines.flow.Flow

/**
 *
 * Created by Lukmanul Hakim on  15/02/24
 * devs.lukman@gmail.com
 */

interface Repository {

    val isLogged: Flow<Boolean>

    fun loginWithEmailPassword(request: RequestLogin, callback: (Resource<Unit>) -> Unit)

    fun registerWithEmailPassword(request: RequestRegister, callback: (Resource<Unit>) -> Unit)

    fun logout()
}