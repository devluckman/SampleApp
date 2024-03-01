package com.edu.filmku.ui.main.saved

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.filmku.domain.model.DetailMovieModel
import com.edu.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * Created by Lukmanul Hakim on  01/03/24
 * devs.lukman@gmail.com
 */
@HiltViewModel
class SavedViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _favoriteData = MutableLiveData<List<DetailMovieModel>>()
    val favoriteData = _favoriteData
    private fun getAllMovieFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMovieFavorite().collect {
                _favoriteData.postValue(it)
            }
        }
    }

    init {
        getAllMovieFavorite()
    }
}