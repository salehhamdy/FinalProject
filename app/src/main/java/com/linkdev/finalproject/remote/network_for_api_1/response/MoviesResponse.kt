package com.linkdev.finalproject.remote.network_for_api_1.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @SerializedName("page")
    val page:Int ?=null,

    @SerializedName("total_pages")
    val totalPages: Int?=null,

    @SerializedName("total_results")
    val totalResults: Int?=null,

    @SerializedName("results")
    val results: ArrayList<Tv>?=null


)

