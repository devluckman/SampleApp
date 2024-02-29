package com.edu.filmku.domain.model

/**
 *
 * Created by Lukmanul Hakim on  26/02/24
 * devs.lukman@gmail.com
 */
data class MovieDetailModel(
    val title: String,
    val poster: String,
    val rating: String,
    val duration: String,
    val genre: List<String>,
    val description: String,
)


