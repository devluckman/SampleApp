package com.edu.filmku.data.remote

import com.edu.filmku.data.response.NowPlayingResponse
import retrofit2.http.GET
import retrofit2.http.Header

/**
 *
 * Created by Lukmanul Hakim on  26/02/24
 * devs.lukman@gmail.com
 */
interface ApiMovieDB {

    @GET("3/movie/now_playing")
    suspend fun getNowPlaying(
        @Header("Authorization") token: String
    ): NowPlayingResponse



}