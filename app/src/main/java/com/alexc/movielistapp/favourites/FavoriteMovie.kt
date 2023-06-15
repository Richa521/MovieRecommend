package com.alexc.movielistapp.favourites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovie(
    @PrimaryKey val id: String,
    val title: String,
    val year: String,
    val genres: String,
    // Add other necessary fields
)
