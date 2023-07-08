package com.alexc.movielistapp.core.ForYou

import android.util.Log
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.core.moviedetails.MovieDetailsViewModel
import com.alexc.movielistapp.core.search.SearchState
import com.alexc.movielistapp.core.search.SearchViewModel
import com.alexc.movielistapp.data.model.MovieDetails
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.data.models.MovieItem
import com.alexc.movielistapp.favourites.FavoritesViewModel
import com.alexc.movielistapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import java.nio.file.Files.list
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {


    var state by mutableStateOf(SearchState())
    private var searchJob: Job? = null
    var rec_list_id: MutableList<Result> = arrayListOf()
    var rec_list_id1: MutableList<Result> = arrayListOf()
    var recommendations: List<String> = mutableListOf()
    var listrecommenfation: MutableList<MovieDetails> = mutableListOf()
//    val rec_movies=


    // Usage example
//    suspend fun main() {
//        val makeMovieRecommendations = MakeMovieRecommendations()
//
//        val favoriteMovies = listOf("Bajrangi Bhaijaan", "Garv", "Race 3", "Wanted")
//        // recommendations = makeMovieRecommendations.makeRecommendationsRequest(favoriteMovies)
//      // recommendations = listOf("Kabir Singh", "Padmaavat", " Queen", " Dangal", "Zindagi Na Milegi Dobara")
//        Log.i("recommendations", "Here " + recommendations)
//
//    }

    suspend fun runLoop(list12:List<String>  ) :Resource<MutableList<Result>>{

         val response=try {
           research(list12)
         }
         catch (e:Exception)
         {
             return Resource.Error(e.stackTraceToString())

         }

       return     Resource.Success(response)

        Log.d("Anike",rec_list_id.toString())
       // rec_list_id1.addAll(rec_list_id)
    }
suspend fun research(list12: List<String>):MutableList<Result>
{
    val makeMovieRecommendations = MakeMovieRecommendations()
    recommendations = makeMovieRecommendations.makeRecommendationsRequest(list12)
    Log.i("recommendations", "Here " + recommendations)
    Log.i("recommendations", "Here " + list12)
 rec_list_id.clear()
   for(i in recommendations)
   {
       val listit=repository.getMoviesBySearch(i)
       rec_list_id.add(listit.data!![0])
   }
    return rec_list_id
}
    fun onSearchBackend(searchString: String) {
        state = state.copy(searchTerm = searchString)
        searchJob = viewModelScope.launch {
            searchMovieBackend()

        }
    }


    private suspend fun searchMovieBackend() {
        state = state.copy(isLoading = true)
        val result = repository.getMoviesBySearch(searchTerm = state.searchTerm)
        when (result) {
            is Resource.Success -> {
                state = state.copy(
                    isLoading = false,
                    isError = false,
                    movies = result.data ?: emptyList()
                )
                println("Sucess")
//                val id=state.movies[0].id
//                rec_list_id.add(id)
                for(i in state.movies)
                {
                    if(rec_list_id.contains(i)==true)
                    {
                            break

                    }

                    else
                    {
                       rec_list_id.add(i)
                        break
                    }
                }
               // Log.d("Aniket", rec_list_id.toString())

            }

            is Resource.Error -> {
                state = state.copy(
                    isLoading = false,
                    isError = true
                )
                println("Errror")
            }

        }
        //list()
    }

    }



