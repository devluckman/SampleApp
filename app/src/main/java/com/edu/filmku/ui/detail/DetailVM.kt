package com.edu.filmku.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.filmku.domain.model.CastMovieData
import com.edu.filmku.domain.model.DetailMovieModel
import com.edu.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * Created by Lukmanul Hakim on  29/02/24
 * devs.lukman@gmail.com
 */
@HiltViewModel
class DetailVM @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _detailDataMovie = MutableLiveData<DetailMovieModel>()
    val detailDataMovie = _detailDataMovie
    fun getDetailMovie(id : Int) {
        viewModelScope.launch {
            repository.getDetailMovie(id).collect {
                _detailDataMovie.value = it
            }
        }
    }

    private val _castMovieData = MutableLiveData<List<CastMovieData>>()
    val castMovieData = _castMovieData
    fun getCastMovie(id : Int) {
        viewModelScope.launch {
            repository.getCast(id).collect {
                _castMovieData.value = it
            }
        }
    }

    private val _favoriteState = MutableLiveData<Boolean>()
    val favoriteState = _favoriteState
    fun updateDataFavorite(data: DetailMovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateOrDeleteMovieInFavorite(data).collect {
                _favoriteState.postValue(it)
            }
        }
    }
}