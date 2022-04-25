package com.alexc.movielistapp.data.models

import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("image") var image: String = "",
    @SerializedName("asCharacter") var asCharacter: String = ""
)