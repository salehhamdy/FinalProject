package com.linkdev.finalproject.screens.moviedetailsscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkdev.finalproject.data.remote.networkformoviesapi.response.MovieDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieDetailsRepository: MovieDetailsReprository) : ViewModel() {
    private val _movieDetailsState = MutableLiveData<MovieDetailsResponse?>()
    val moviesState: LiveData<MovieDetailsResponse?> get() = _movieDetailsState

    fun fetchMovieDetails(id :Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val moviesResponse = movieDetailsRepository.getMovieDetails(id)
            _movieDetailsState.postValue(moviesResponse)
        }
    }
}