package com.edu.filmku.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edu.filmku.BuildConfig
import com.edu.filmku.data.local.PreferencesHelper
import com.edu.filmku.data.local.PreferencesImpl
import com.edu.filmku.data.remote.ApiMovieDB
import com.edu.filmku.data.repository.RepositoryImpl
import com.edu.filmku.data.utils.OkhttpClient
import com.edu.filmku.domain.local.Preferences
import com.edu.filmku.domain.repository.Repository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.edu.filmku.data.database.MovieDatabase
import com.edu.filmku.data.database.dao.MovieDao

/**
 *
 * Created by Lukmanul Hakim on  17/02/24
 * devs.lukman@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    // region Preferences
    @Provides
    fun providePreferenceHelper(
        @ApplicationContext context: Context
    ): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Provides
    fun providePreference(
        helper: PreferencesHelper
    ): Preferences {
        return PreferencesImpl(helper)
    }

    // endregion

    // region Database

    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "my_cinema_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideDaoMovie(movieDatabase: MovieDatabase) : MovieDao {
        return movieDatabase.movieDao()
    }

    // endregion

    // region Firebase

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    // endregion

    // region API

    @Provides
    fun provideApi(): ApiMovieDB {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_API)
            .client(OkhttpClient.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiMovieDB::class.java)
    }

    @Provides
    fun provideRepository(
        firebaseAuth: FirebaseAuth,
        apiMovieDB: ApiMovieDB,
        preferences: Preferences,
        movieDao: MovieDao
    ): Repository {
        return RepositoryImpl(
            firebaseAuth = firebaseAuth,
            apiMovieDB = apiMovieDB,
            preferences = preferences,
            database = movieDao
        )
    }

    // endregion
}