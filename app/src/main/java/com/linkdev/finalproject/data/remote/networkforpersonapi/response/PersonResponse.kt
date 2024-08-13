package com.linkdev.finalproject.data.remote.networkforpersonapi.response

import com.google.gson.annotations.SerializedName

data class PersonResponse(

    @SerializedName("page")
    val page:Int?=null,

    @SerializedName("results")
    val results: ArrayList<PersonResponse>?=null,

    @SerializedName("adult")
    val isAdult:Boolean ?=null,

    @SerializedName("vote_average")
    val averageVote:Double?=null,

    @SerializedName("original_name")
    val name: String? = null,

    @SerializedName("profile_path")
    val profilePath: String?=null,

    @SerializedName("known_for_department")
    val department: String?=null
)
