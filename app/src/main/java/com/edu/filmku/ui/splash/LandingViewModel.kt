package com.edu.filmku.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.filmku.domain.repository.Repository
import com.edu.filmku.domain.request.RequestRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * Created by Lukmanul Hakim on  15/02/24
 * devs.lukman@gmail.com
 */

// TODO(4) Explain about ViewModel

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    // TODO(5) Goto Firebase Implementation, Explain About
    private val _isLogged = MutableLiveData<Boolean>()
    val isLogged: LiveData<Boolean> get() = _isLogged

    private fun checkLoginState() {
        viewModelScope.launch {
            repository.isLogged.collect {
                _isLogged.value = it
            }
        }
    }

    init {
        checkLoginState()
    }
}