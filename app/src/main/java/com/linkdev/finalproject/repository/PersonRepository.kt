package com.linkdev.finalproject.repository

import com.linkdev.finalproject.remote.network_for_api_2.ApiPerson
import com.linkdev.finalproject.remote.network_for_api_2.response.PersonResponse
import javax.inject.Inject


class PersonRepository @Inject constructor(private val apiPerson: ApiPerson) {

    suspend fun getPersonTrending(): PersonResponse? {
        val response = apiPerson.getPersonTrending()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
