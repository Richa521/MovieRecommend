package com.alexc.movielistapp.core.moviedetails.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.core.homescreen.views.MovieCard
import com.alexc.movielistapp.data.model.MovieDetails
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.data.models.SimilarMovieItem
import com.alexc.movielistapp.ui.theme.OpenSans

@Composable
fun SimilarMoviesList(
    navController: NavController,
    similarList: List<Result>? = arrayListOf<Result>()
) {

    val movieList = similarList!!.map {
        Result(id = it.id, title = it.title, poster_path = it.poster_path)
    }

    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 0.dp)
    ) {
        Text(
            "Similar to this",
            fontSize = 20.sp,
            color = MaterialTheme.colors.primary,
            fontFamily = OpenSans,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 7.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow {
            items(movieList.size) {
               MovieCard(movie = movieList[it], navController = navController)
            }
        }
    }
}
