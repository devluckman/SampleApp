package com.edu.filmku.domain.repository

import com.edu.filmku.data.network.Resource
import com.edu.filmku.domain.model.CastMovieData
import com.edu.filmku.domain.model.ItemMovieModel
import com.edu.filmku.domain.model.DetailMovieModel
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

    fun getNowPlayingMovie() : Flow<List<ItemMovieModel>>
    fun getPopularMovie() : Flow<List<ItemMovieModel>>

    fun getDetailMovie(idMovie : Int) : Flow<DetailMovieModel>
    fun getCast(idMovie : Int) : Flow<List<CastMovieData>>

    fun getAllMovieFavorite() : Flow<List<DetailMovieModel>>

    fun updateOrDeleteMovieInFavorite(data : DetailMovieModel) : Flow<Boolean>
}