package com.edu.filmku.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edu.filmku.domain.model.DetailMovieModel

/**
 *
 * Created by Lukmanul Hakim on  01/03/24
 * devs.lukman@gmail.com
 */

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAllMovieBookmark(): List<DetailMovieModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieToBookmark(data: DetailMovieModel)

    @Delete
    fun deleteMovieFromBookmark(data: DetailMovieModel)


    @Query("SELECT * FROM movie_table WHERE id=:id")
    suspend fun findMovie(id: Int): DetailMovieModel?
}