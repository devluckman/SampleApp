package com.edu.filmku.di

import com.edu.filmku.BuildConfig
import com.edu.filmku.data.remote.ApiMovieDB
import com.edu.filmku.data.repository.RepositoryImpl
import com.edu.filmku.data.utils.OkhttpClient
import com.edu.filmku.domain.repository.Repository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        apiMovieDB: ApiMovieDB
    ): Repository {
        return RepositoryImpl(
            firebaseAuth = firebaseAuth,
            apiMovieDB = apiMovieDB
        )
    }
}