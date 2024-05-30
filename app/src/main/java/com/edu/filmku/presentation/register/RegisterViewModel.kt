package com.edu.filmku.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edu.filmku.data.network.Resource
import com.edu.filmku.domain.repository.Repository
import com.edu.filmku.domain.request.RequestRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 * Created by Lukmanul Hakim on  17/02/24
 * devs.lukman@gmail.com
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _registerState = MutableLiveData<Resource<Unit>>()
    val registerState: LiveData<Resource<Unit>> get() = _registerState

    fun register(requestRegister: RequestRegister) {
        repository.registerWithEmailPassword(requestRegister) {
            _registerState.value = it
        }
    }

}