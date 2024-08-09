package com.linkdev.finalproject.viewmodel

import androidx.lifecycle.*
import com.linkdev.finalproject.remote.network_for_api_1.response.MoviesResponse
import com.linkdev.finalproject.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _moviesState = MutableLiveData<MoviesResponse?>()
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
