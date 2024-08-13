package com.linkdev.finalproject.screens.moviesscreen

import androidx.lifecycle.*
import com.linkdev.finalproject.data.remote.networkformoviesapi.response.MoviesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _moviesState = MutableLiveData<MoviesResponse?>(null)
    val moviesState: LiveData<MoviesResponse?> get() = _moviesState

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val moviesResponse = repository.getMoviesTrending()
            _moviesState.postValue(moviesResponse)
        }
    }
}