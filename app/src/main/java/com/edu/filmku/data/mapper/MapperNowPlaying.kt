package com.edu.filmku.data.mapper

import com.edu.filmku.BuildConfig
import com.edu.filmku.data.response.NowPlayingResponse
import com.edu.filmku.domain.model.ItemMovieModel
import kotlin.math.roundToInt

/**
 *
 * Created by Lukmanul Hakim on  26/02/24
 * devs.lukman@gmail.com
 */
object MapperNowPlaying {

    fun toDomain(data : NowPlayingResponse) : List<ItemMovieModel> {
        return data.results.map {
            ItemMovieModel(
                title = it.originalTitle,
                rating = String.format("%.1f", it.voteAverage),
                poster = BuildConfig.BASE_URL_IMAGE + it.posterPath
            )
        }
    }

}