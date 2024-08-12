package com.linkdev.finalproject.repository

import com.linkdev.finalproject.data.remote.networkformoviesapi.ApiMovies
import com.linkdev.finalproject.data.remote.networkformoviesapi.response.MoviesResponse
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiMovies: ApiMovies) {

    suspend fun getMoviesTrending(): MoviesResponse? {
        val response = apiMovies.getMoviesTrending()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}