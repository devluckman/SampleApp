package com.edu.filmku.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.filmku.domain.model.CastMovieData
import com.edu.filmku.domain.model.ItemMovieModel
import com.edu.filmku.domain.model.MovieDetailModel
import com.edu.filmku.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _detailDataMovie = MutableLiveData<MovieDetailModel>()
    val detailDataMovie = _detailDataMovie
    fun getDetailMovie(id : String?) {
        viewModelScope.launch {
            repository.getDetailMovie(id.orEmpty()).collect {
                _detailDataMovie.value = it
            }
        }
    }

    private val _castMovieData = MutableLiveData<List<CastMovieData>>()
    val castMovieData = _castMovieData
    fun getCastMovie(id : String?) {
        viewModelScope.launch {
            repository.getCast(id.orEmpty()).collect {
                _castMovieData.value = it
            }
        }
    }
}