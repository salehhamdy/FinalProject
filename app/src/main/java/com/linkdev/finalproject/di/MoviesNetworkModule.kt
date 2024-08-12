package com.linkdev.finalproject.di

import com.linkdev.finalproject.data.remote.networkformoviesapi.ApiMovies
import com.linkdev.finalproject.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesNetworkModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiMovies {
        return retrofit.create(ApiMovies::class.java)
    }
}
