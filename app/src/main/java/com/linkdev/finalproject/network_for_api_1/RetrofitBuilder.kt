package com.linkdev.finalproject.network_for_api_1

import com.linkdev.finalproject.network_for_api_1.NetworkConstants.TIME_30
import com.linkdev.finalproject.network_for_api_1.NetworkConstants.TIME_60
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS


class RetrofitBuilder {

    companion object {
        fun getAPIService(): Api {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHTTPClient())
                .build()
            val api = retrofit.create(Api::class.java)
            return api
        }

        //Response time out for when the service do not send
        private fun createOkHTTPClient(): OkHttpClient {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor().setLevel(BODY)
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(TIME_30, SECONDS)
                .writeTimeout(TIME_30, SECONDS)
                .connectTimeout(TIME_30, SECONDS)//
                .build()
        }
    }

}