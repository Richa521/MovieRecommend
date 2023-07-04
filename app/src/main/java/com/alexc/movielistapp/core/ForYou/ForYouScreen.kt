package com.alexc.movielistapp.core.ForYou

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.core.bottombar.BottomBar
import com.alexc.movielistapp.core.movielist.MovieListCard
import com.alexc.movielistapp.core.search.SearchViewModel
import com.alexc.movielistapp.core.search.views.SearchInfoView
import com.alexc.movielistapp.core.search.views.SearchResultCard
import com.alexc.movielistapp.core.watchlist.MovieListItem
import com.alexc.movielistapp.data.model.MovieDetails
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.data.remote.MoviesApi
import com.alexc.movielistapp.repository.MovieRepository
import com.alexc.movielistapp.ui.theme.OpenSans
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.IOException

@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForYouScreen(
    navController: NavController,
    viewModel:ForYouViewModel = hiltViewModel()
) {
        viewModel.runLoop()
    val movieList = viewModel.rec_list_id
    Log.d("Aniket", viewModel.rec_list_id.toString())
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        if (!movieList.isNullOrEmpty()) {
            val itemCount = if (movieList!!.size % 2 == 0) {
                movieList!!.size / 2
            } else {
                movieList!!.size / 2 + 1
            }
            items(itemCount) {
                MovieListRow(rowIndex = it, movies = movieList, navController = navController)
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

            CircularProgressIndicator(color = MaterialTheme.colors.primary)


    }
}


@Composable
fun MovieListRow(
    rowIndex: Int,
    movies: List<Result>,
    navController: NavController
) {
    Column {
        Row {
            MovieListCard(
                movieItem = movies[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (movies.size >= rowIndex * 2 + 2) {
                MovieListCard(
                    movieItem = movies[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
