package com.edu.filmku.di

import android.content.Context
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
import retrofit2.create

/**
 *
 * Created by Lukmanul Hakim on  17/02/24
 * devs.lukman@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

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

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

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
        preferences: Preferences
    ): Repository {
        return RepositoryImpl(
            firebaseAuth = firebaseAuth,
            apiMovieDB = apiMovieDB,
            preferences = preferences
        )
    }
}