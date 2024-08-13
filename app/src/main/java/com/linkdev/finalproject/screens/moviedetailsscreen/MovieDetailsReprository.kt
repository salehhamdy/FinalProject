package com.linkdev.finalproject.screens.moviedetailsscreen

import com.linkdev.finalproject.data.remote.networkformoviesapi.ApiMovies
import com.linkdev.finalproject.data.remote.networkformoviesapi.response.MovieDetailsResponse
import javax.inject.Inject


class MovieDetailsReprository @Inject constructor(private val apiMovies: ApiMovies) {

    suspend fun getMovieDetails(movieId: Int) : MovieDetailsResponse? {
        val response = apiMovies.getMoviesDetails(movieId)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

}