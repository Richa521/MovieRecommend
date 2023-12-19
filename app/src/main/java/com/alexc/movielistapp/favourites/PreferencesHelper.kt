package com.alexc.movielistapp.favourites


import com.alexc.movielistapp.data.models.MovieItem
import com.google.gson.reflect.TypeToken
import android.content.Context
import android.content.SharedPreferences
import android.telecom.Call
import com.alexc.movielistapp.data.model.MovieDetails
import com.alexc.movielistapp.data.model.Result
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException


class PreferencesHelper(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(
        "FavoritesPreferences",
        Context.MODE_PRIVATE
    )
    val prefs = PRefs(context)
    fun saveFavorites(favorites:List<MovieDetails>) {
        val gson = Gson()
        val jsonFavorites = gson.toJson(favorites)
        preferences.edit().putString("favorites", jsonFavorites).apply()
        prefs.favouritesChange = 1
    }

    fun getFavorites(): List<MovieDetails> {
        val gson = Gson()
        val jsonFavorites = preferences.getString("favorites", null)
        return if (!jsonFavorites.isNullOrEmpty()) {
            try {
                val movieItems = gson.fromJson(jsonFavorites, Array<MovieDetails>::class.java)
                movieItems.filter { it.genres is List<*> }
            } catch (e: JsonSyntaxException) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }


    fun saveWatchlist(watchlist: List<MovieDetails>) {
        val gson = Gson()
        val jsonWatchlist = gson.toJson(watchlist)
        preferences.edit().putString("watchlist", jsonWatchlist).apply()
    }

    fun getWatchlist(): List<MovieDetails> {
        val gson = Gson()
        val jsonWatchlist = preferences.getString("watchlist", null)
        return if (!jsonWatchlist.isNullOrEmpty()) {
            try {
                val movieItems = gson.fromJson(jsonWatchlist, Array<MovieDetails>::class.java)
                movieItems.filter { it.genres is List<*> }
            } catch (e: JsonSyntaxException) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    fun saveRecommendations(recommendations:List<Result>) {
        val gson = Gson()
        val jsonRecommendations = gson.toJson(recommendations)
        preferences.edit().putString("recommendations", jsonRecommendations).apply()
        prefs.favouritesChange = 1
    }

    fun getRecommendations(): List<Result> {
        val gson = Gson()
        val jsonRecommendations = preferences.getString("recommendations", null)
        return if (jsonRecommendations != null) {
            gson.fromJson(jsonRecommendations, Array<Result>::class.java).toList()
        } else {
            emptyList()
        }
    }

    fun saveSavedMovieIds(savedMovieIds: List<String>) {
        val gson = Gson()
        val jsonSavedMovieIds = gson.toJson(savedMovieIds)
        preferences.edit().putString("savedMovieIds", jsonSavedMovieIds).apply()
    }

    fun getSavedMovieIds(): List<String> {
        val gson = Gson()
        val jsonSavedMovieIds = preferences.getString("savedMovieIds", null)
        return if (jsonSavedMovieIds != null) {
            gson.fromJson(jsonSavedMovieIds, Array<String>::class.java).toList()
        } else {
            emptyList()
        }
    }

    fun saveNearestMatchMovies(movies: List<MovieItem>) {
        val gson = Gson()
        val jsonMovies = gson.toJson(movies)
        preferences.edit().putString("nearestMatchMovies", jsonMovies).apply()
    }

    fun getNearestMatchMovies(): List<MovieItem> {
        val gson = Gson()
        val jsonMovies = preferences.getString("nearestMatchMovies", null)
        return if (jsonMovies != null) {
            gson.fromJson(jsonMovies, Array<MovieItem>::class.java).toList()
        } else {
            emptyList()
        }
    }
}


