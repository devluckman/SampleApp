package com.edu.filmku.data.mapper

import com.edu.filmku.BuildConfig
import com.edu.filmku.data.response.NowPlayingResponse
import com.edu.filmku.data.response.PopularResponse
import com.edu.filmku.domain.model.ItemMovieModel

/**
 *
 * Created by Lukmanul Hakim on  26/02/24
 * devs.lukman@gmail.com
 */
object Mapper {

    fun nowPlayingToDomain(data : NowPlayingResponse) : List<ItemMovieModel> {
        return data.results.map {
            ItemMovieModel(
                title = it.originalTitle,
                rating = String.format("%.1f/10", it.voteAverage),
                poster = BuildConfig.BASE_URL_IMAGE + it.posterPath
            )
        }
    }

    fun popularToDomain(data : PopularResponse) : List<ItemMovieModel> {
        return data.results.map {
            ItemMovieModel(
                title = it.originalTitle,
                rating = String.format("%.1f/10", it.voteAverage),
                poster = BuildConfig.BASE_URL_IMAGE + it.posterPath
            )
        }
    }

}