package com.linkdev.finalproject.network_for_api_1.response

import com.google.gson.annotations.SerializedName

data class Tv (

    @SerializedName("id")
    val id:Int ?=null,

    @SerializedName("adult")
    val isAdult:Boolean ?=null,

    @SerializedName("vote_average")
    val averageVote:Double?=null,

    @SerializedName("original_title")
    val title: String? = null,

    @SerializedName("poster_path")
    val posterPath: String?=null,

    @SerializedName("overview")
    val overview: String?=null

)