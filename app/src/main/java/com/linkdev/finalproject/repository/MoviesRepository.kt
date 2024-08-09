package com.linkdev.finalproject.repository

import com.linkdev.finalproject.remote.network_for_api_1.Api
import com.linkdev.finalproject.remote.network_for_api_1.response.MoviesResponse
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: Api) {

    suspend fun getMoviesTrending(): MoviesResponse? {
        val response = api.getMoviesTrending()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}