package com.alexc.movielistapp.data.model

data class Result(
    var adult: Boolean=true,
    var backdrop_path: String="",
    var genre_ids: List<Int> = arrayListOf(),
    var id: Int=0,
    var original_language: String="",
    var original_title: String="",
    var overview: String="",
    var popularity: Double=0.0,
    var poster_path: String="",
    var release_date: String="",
    var title: String="",
    var video: Boolean=false,
    var vote_average: Double=0.0,
    var vote_count: Int=0
)