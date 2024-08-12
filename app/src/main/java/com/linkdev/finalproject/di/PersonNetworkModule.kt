package com.example.finalproject.di

import com.linkdev.finalproject.data.remote.networkforpersonapi.ApiPerson
import com.linkdev.finalproject.repository.PersonRepository
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

    @Provides
    @Singleton
    fun providePersonRepository(apiPerson: ApiPerson): PersonRepository {
        return PersonRepository(apiPerson)
    }
}

