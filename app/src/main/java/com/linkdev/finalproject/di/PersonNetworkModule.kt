package com.example.finalproject.di

import com.linkdev.finalproject.remote.network_for_api_2.ApiPerson
import com.linkdev.finalproject.remote.network_for_api_2.NetworkConstantsPerson

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object PersonNetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .readTimeout(NetworkConstantsPerson.TIME_30, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstantsPerson.TIME_30, TimeUnit.SECONDS)
            .connectTimeout(NetworkConstantsPerson.TIME_30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstantsPerson.BASE_URL_Person)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiPerson(retrofit: Retrofit): ApiPerson {
        return retrofit.create(ApiPerson::class.java)
    }
}
