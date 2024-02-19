package com.edu.filmku.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edu.filmku.data.network.Resource
import com.edu.filmku.domain.repository.Repository
import com.edu.filmku.domain.request.RequestLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 * Created by Lukmanul Hakim on  16/02/24
 * devs.lukman@gmail.com
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _loginStateWithEmailPassword = MutableLiveData<Resource<Unit>>()
    val loginState: LiveData<Resource<Unit>> get() = _loginStateWithEmailPassword

    fun loginWithEmailPassword(email : String, password : String) {
        repository.loginWithEmailPassword(RequestLogin(email, password)) {
            _loginStateWithEmailPassword.value = it
        }
    }

}