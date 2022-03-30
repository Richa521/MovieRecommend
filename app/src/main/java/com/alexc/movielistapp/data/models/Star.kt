package com.alexc.movielistapp.data.models

import com.google.gson.annotations.SerializedName

data class Star(
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = ""
)
