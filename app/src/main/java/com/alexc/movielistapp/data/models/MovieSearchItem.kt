package com.alexc.movielistapp.data.models

import com.google.gson.annotations.SerializedName

data class MovieSearchItem(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("image")
    var image: String = "",
)
