package com.alexc.movielistapp.core.ForYou

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MakeMovieRecommendations {

     suspend fun makeRecommendationsRequest (favoriteMovies: List<String>): List<String> {
        val client = OkHttpClient()
        val msg =
            "You are a professional movie recommender based on the given list of movies suggest me 5 more movies with just comma seperated format without any prefacing or introductory text givenlist is $favoriteMovies"
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = JSONObject().apply {
            put("model", "gpt-3.5-turbo")
            put("messages", JSONArray().put(JSONObject().apply {
                put("role", "user")
                put("content", "$msg")
            }))
        }
        val sendrequest = requestBody.toString().toRequestBody(mediaType)
            val request = Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Content-Type", "application/json")
                .addHeader(
                    "Authorization",
                    "Bearer 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"
                )
                .post(sendrequest)
                .build()
            val response = fetchResponseBody(client,request)
        val result=parseMovieList(response)
        return result
    }
    fun parseMovieList(responseBody: String): List<String>{
        val jsonObject = JSONObject(responseBody)
        val jsonArray = jsonObject.getJSONArray("choices")
        val messageObject = jsonArray.getJSONObject(0).getJSONObject("message")
        val result = messageObject.getString("content")
        val movielist= result.split(",").map { it.trim() }
        return movielist
    }

    suspend fun fetchResponseBody(client: OkHttpClient, request: Request): String {
        return withContext(Dispatchers.IO) {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) throw IOException("Unexpected code ${response.code}")
            val responseBody = response.body?.string() ?: throw IOException("Empty response body")
            response.close()
            responseBody
        }
    }
}