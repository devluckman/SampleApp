package com.edu.filmku.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edu.filmku.data.database.dao.MovieDao
import com.edu.filmku.domain.model.DetailMovieModel

/**
 *
 * Created by Lukmanul Hakim on  01/03/24
 * devs.lukman@gmail.com
 */
@Database(entities = [DetailMovieModel::class], version = 2)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

}