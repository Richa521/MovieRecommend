package com.alexc.movielistapp.data.models

import com.google.gson.annotations.SerializedName

data class RequestItemsList(
    @SerializedName("items")
    var items: List<MovieListItem> = arrayListOf(),
)
