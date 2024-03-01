package com.edu.filmku.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * Created by Lukmanul Hakim on  26/02/24
 * devs.lukman@gmail.com
 */
@Entity(tableName = "movie_table")
data class DetailMovieModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val poster: String,
    val backdrop: String,
    val rating: String,
    val duration: String,
    val genre: String,
    val description: String,
    val isFavorite : Boolean = false
)


