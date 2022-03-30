package com.alexc.movielistapp.data.models

import com.google.gson.annotations.SerializedName

data class MovieListItem(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("fullTitle")
    var fullTitle: String = "",
    @SerializedName("year")
    var year: String = "",
    @SerializedName("image")
    var image: String = "",
)
