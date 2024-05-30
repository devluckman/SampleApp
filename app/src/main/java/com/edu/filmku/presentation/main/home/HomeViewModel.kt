package com.edu.filmku.presentation.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.filmku.domain.model.ItemMovieModel
import com.edu.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * Created by Lukmanul Hakim on  26/02/24
 * devs.lukman@gmail.com
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _nowPlayingData = MutableLiveData<List<ItemMovieModel>>()
    val nowPlayingData = _nowPlayingData
    private fun getNowPlaying() {
        viewModelScope.launch {
            repository.getNowPlayingMovie().collect {
                _nowPlayingData.value = it
            }
        }
    }

    private val _popularData = MutableLiveData<List<ItemMovieModel>>()
    val popularData = _popularData
    private fun getPopular() {
        viewModelScope.launch {
            repository.getPopularMovie().collect {
                _popularData.value = it
            }
        }
    }

    fun logout() {
        repository.logout()
    }

    init {
        getNowPlaying()
        getPopular()
    }
}