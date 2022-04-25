package com.alexc.movielistapp.data.models

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("originalTitle")
    var originalTitle: String = "",
    @SerializedName("fullTitle")
    var fullTitle: String = "",
    @SerializedName("type")
    var type: String = "",
    @SerializedName("year")
    var year: String = "",
    @SerializedName("image")
    var image: String = "",
    @SerializedName("releaseDate")
    var releaseDate: String = "",
    @SerializedName("runtimeMins")
    var runtimeMins: String = "",
    @SerializedName("runtimeStr")
    var runtimeStr: String = "",
    @SerializedName("plot")
    var plot: String = "",
    @SerializedName("plotLocal")
    var plotLocal: String = "",
    @SerializedName("plotLocalIsRtl")
    var plotLocalIsRtl: String = "",
    @SerializedName("awards")
    var awards: String = "",
    @SerializedName("directors")
    var directors: String = "",
    @SerializedName("directorList")
    var directorList: ArrayList<Director> = arrayListOf(),
    @SerializedName("writers")
    var writers: String = "",
    @SerializedName("writerList")
    var writerList: ArrayList<Writer> = arrayListOf(),
    @SerializedName("starList")
    var starList: ArrayList<Star> = arrayListOf(),
    @SerializedName("actorList")
    var actorsList: ArrayList<Actor> = arrayListOf(),
    @SerializedName("genres")
    var genres: String = "Adventure, Family, Fantasy",
    @SerializedName("imDbRating")
    var imDbRating: String = "",
    @SerializedName("similars")
    var similarsList: ArrayList<SimilarMovieItem> = arrayListOf(),
)
