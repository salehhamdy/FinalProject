package com.linkdev.finalproject

fun String?.fullImageURL():String?{

    val fullURL= if(!isNullOrBlank()) {
        "https://image.tmdb.org/t/p/w500/$this"
    }else {
        this
    }

    return fullURL

}