package com.example.finalproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkdev.finalproject.remote.network_for_api_2.response.PersonResponse
import com.linkdev.finalproject.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(private val personRepository: PersonRepository) : ViewModel()
{
    private val _personState = MutableLiveData<PersonResponse?>(null)
    val personState: LiveData<PersonResponse?> get() = _personState

    init {
        getPersonDetails()
    }

    private fun getPersonDetails() {
        viewModelScope.launch {
            try {
                val personDetails = personRepository.getPersonTrending()
                _personState.value = personDetails
            } catch (e: Exception) {
                // Handle the exception (you might want to log it or show an error message)
                _personState.value = null
            }
        }
    }
}