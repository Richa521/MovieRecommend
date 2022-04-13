package com.alexc.movielistapp.data.models

import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("image") var image: String = "",
    @SerializedName("asCharacter") var asCharacter: String = ""
)


fun Actor.default(): Actor {
    id = "nm0586568"
    name = "Mads Mikkelsen"
    image = "https://imdb-api.com/images/original/MV5BMTcyMTU5MzgxMF5BMl5BanBnXkFtZTYwMDI0NjI1._V1_Ratio1.0000_AL_.jpg"
    asCharacter = "Gellert Grindelwaldas Gellert Grindelwald"
    return this
}

fun Actor.default2(): Actor {
    id = "nm3009232"
    name = "Ezra Miller"
    image = "https://imdb-api.com/images/original/MV5BOTQ2YmNlZDEtM2EwNi00N2JhLTk3ZDktMjBmNzYwYWI1OWZmXkEyXkFqcGdeQXVyNjk1MjYyNTA@._V1_Ratio1.0000_AL_.jpg"
    asCharacter = "Credence Bareboneas Credence Barebone…"
    return this
}