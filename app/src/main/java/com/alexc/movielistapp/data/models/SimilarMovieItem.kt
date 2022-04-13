package com.alexc.movielistapp.data.models

import com.google.gson.annotations.SerializedName

data class SimilarMovieItem(
    @SerializedName("id") var id: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("image") var image: String = "",
)


fun SimilarMovieItem.default(): SimilarMovieItem {
    id = "tt4123430"
    title = "Fantastic Beasts: The Crimes of Grindelwald"
    image =
        "https://imdb-api.com/images/original/MV5BYWVlMDI5N2UtZTIyMC00NjZkLWI5Y2QtODM5NGE5MzA0NmVjXkEyXkFqcGdeQXVyNzU3NjUxMzE@._V1_Ratio0.6763_AL_.jpg"
    return this
}

fun SimilarMovieItem.default2(): SimilarMovieItem {
    id = "tt3183660"
    title = "Fantastic Beasts and Where to Find Them"
    image =
        "https://imdb-api.com/images/original/MV5BMjMxOTM1OTI4MV5BMl5BanBnXkFtZTgwODE5OTYxMDI@._V1_Ratio0.6763_AL_.jpg"
    return this
}
