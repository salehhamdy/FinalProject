package com.linkdev.finalproject.di

import com.linkdev.finalproject.data.remote.networkforpersonapi.ApiPerson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersonNetworkModule {

    @Provides
    @Singleton
    fun provideApiPerson(retrofit: Retrofit): ApiPerson {
        return retrofit.create(ApiPerson::class.java)
    }

}

