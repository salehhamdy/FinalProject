package com.linkdev.finalproject.remote.network_for_api_1

import com.linkdev.finalproject.remote.network_for_api_1.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {

    @GET(NetworkConstants.Movies_Trending)
    suspend fun getMoviesTrending(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Header("Authorization") token: String = "Bearer YOUR_API_KEY"
    ): Response<MoviesResponse>
}
