package com.alexc.movielistapp.data.models

import com.google.gson.annotations.SerializedName

data class SimilarMovieItem(
    @SerializedName("id") var id: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("image") var image: String = "",
)