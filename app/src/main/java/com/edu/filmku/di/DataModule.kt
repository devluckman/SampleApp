package com.edu.filmku.di

import com.edu.filmku.data.repository.RepositoryImpl
import com.edu.filmku.domain.repository.Repository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 *
 * Created by Lukmanul Hakim on  17/02/24
 * devs.lukman@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideFirebaseAuth() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }


    @Provides
    fun provideRepository(
        firebaseAuth: FirebaseAuth
    ) : Repository {
        return RepositoryImpl(
            firebaseAuth = firebaseAuth
        )
    }
}