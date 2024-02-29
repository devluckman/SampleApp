package com.edu.filmku.data.mapper

import com.edu.filmku.BuildConfig
import com.edu.filmku.data.response.CastResponse
import com.edu.filmku.data.response.DetailMovieResponse
import com.edu.filmku.data.response.NowPlayingResponse
import com.edu.filmku.data.response.PopularResponse
import com.edu.filmku.domain.model.CastMovieData
import com.edu.filmku.domain.model.ItemMovieModel
import com.edu.filmku.domain.model.MovieDetailModel

/**
 *
 * Created by Lukmanul Hakim on  26/02/24
 * devs.lukman@gmail.com
 */
object Mapper {

    fun nowPlayingToDomain(data : NowPlayingResponse) : List<ItemMovieModel> {
        return data.results?.map {
            ItemMovieModel(
                title = it.originalTitle.orEmpty(),
                rating = String.format("%.1f/10", it.voteAverage),
                poster = BuildConfig.BASE_URL_IMAGE + it.posterPath,
                id = "${it.id}"
            )
        } ?: emptyList()
    }

    fun popularToDomain(data : PopularResponse) : List<ItemMovieModel> {
        return data.results?.map {
            ItemMovieModel(
                title = it.originalTitle.orEmpty(),
                rating = String.format("%.1f/10", it.voteAverage),
                poster = BuildConfig.BASE_URL_IMAGE + it.posterPath,
                id = "${it.id}"
            )
        } ?: emptyList()
    }

    fun detailToDomain(data : DetailMovieResponse) : MovieDetailModel {
        return MovieDetailModel(
            title = data.originalTitle.orEmpty(),
            description = data.overview.orEmpty(),
            genre = data.genres?.map { it.name.orEmpty() } ?: emptyList(),
            rating = String.format("%.1f/10", data.voteAverage),
            poster = BuildConfig.BASE_URL_IMAGE + data.backdropPath,
            duration = minutesToHourMinute(data.runtime ?: 0)
        )
    }

    private fun minutesToHourMinute(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60 // Sisa Bagi 113 % 60 = 53 or 200 % 60 = 0

        val hoursStr = if (hours > 0) "$hours h " else ""
        val minutesStr = if (remainingMinutes > 0) "$remainingMinutes m" else ""

        return "$hoursStr$minutesStr"
    }

    fun castToDomain(data : CastResponse) : List<CastMovieData> {
        return data.cast?.map {
            CastMovieData(
                name = it.name.orEmpty(),
                picture = BuildConfig.BASE_URL_IMAGE + it.profilePath
            )
        } ?: emptyList()
    }
}