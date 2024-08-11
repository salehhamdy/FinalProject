package com.linkdev.finalproject.remote.network_for_api_2

import com.linkdev.finalproject.remote.network_for_api_2.response.PersonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

//'https://api.themoviedb.org/3/trending/person/day?language=en-US' \
interface ApiPerson{
    @GET(NetworkConstantsPerson.Person_Trending)

    suspend fun getPersonTrending(
        @Query("language") language:String="en-US",//default value
        @Query("page") page:Int = 1,
        @Header("Authorization") token: String=" Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZDg0YWQ4ODc1YTMzMTQzYmU0ZDM4YzUxZWViYmY4MiIsIm5iZiI6MTcyMjk2MjYxNi41NjAyNTQsInN1YiI6IjY2OWJlMTgwYTQ4ZGY3OWYwMjc3NzQ2ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FAmjOvHm4jj4DzaEHQ5cynUGsKh2wVszxA4mYQq9GUw"
    ):Response<PersonResponse>
}