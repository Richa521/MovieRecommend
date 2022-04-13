package com.alexc.movielistapp.data.models

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("title")
    var title: String = "Fantastic Beasts: The Secrets of Dumbledore",
    @SerializedName("originalTitle")
    var originalTitle: String = "",
    @SerializedName("fullTitle")
    var fullTitle: String = "",
    @SerializedName("type")
    var type: String = "",
    @SerializedName("year")
    var year: String = "2022",
    @SerializedName("image")
    var image: String = "https://imdb-api.com/images/original/MV5BZGQ1NjQyNDMtNzFlZS00ZGIzLTliMWUtNGJkMGMzNTBjNDg0XkEyXkFqcGdeQXVyMTE1NDI5MDQx._V1_Ratio0.6751_AL_.jpg",
    @SerializedName("releaseDate")
    var releaseDate: String = "",
    @SerializedName("runtimeMins")
    var runtimeMins: String = "",
    @SerializedName("runtimeStr")
    var runtimeStr: String = "2h 22min",
    @SerializedName("plot")
    var plot: String = "Professor Albus Dumbledore knows the powerful Dark wizard Gellert Grindelwald " +
            "is moving to seize control of the wizarding world. Unable to stop him alone, he entrusts Magizool" +
            "ogist Newt Scamander to lead an intrepid team of wizards, witches and one brave Muggle baker on a dangerous " +
            "mission, where they encounter old and new beasts and clash with Grindelwald's growing legion of followers. " +
            "But with the stakes so high, how long can Dumbledore remain on the sidelines? (Warner Bros media release)",
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
    var actorsList: ArrayList<Actor> = arrayListOf(Actor().default2(), Actor().default() ),
    @SerializedName("genres")
    var genres: String = "Adventure, Family, Fantasy",
    @SerializedName("imDbRating")
    var imDbRating: String = "6.5",
    @SerializedName("similars")
    var similarsList: ArrayList<SimilarMovieItem> = arrayListOf(SimilarMovieItem().default(), SimilarMovieItem().default2()),
)
