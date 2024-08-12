package com.linkdev.finalproject.repository

import com.linkdev.finalproject.data.remote.networkforpersonapi.ApiPerson
import com.linkdev.finalproject.data.remote.networkforpersonapi.response.PersonResponse
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
