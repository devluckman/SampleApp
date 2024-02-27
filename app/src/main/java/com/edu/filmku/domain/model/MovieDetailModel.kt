package com.edu.filmku.domain.model

/**
 *
 * Created by Lukmanul Hakim on  26/02/24
 * devs.lukman@gmail.com
 */
data class MovieDetailModel(
    val title: String,
    val poster: String,
    val imdb: String,
    val rating: String,
    val duration: String,
    val genre: List<String>,
    val language: String,
    val description: String,
    val cast: List<CastData>
) {
    data class CastData(
        val picture: String,
        val name: String
    )
}


