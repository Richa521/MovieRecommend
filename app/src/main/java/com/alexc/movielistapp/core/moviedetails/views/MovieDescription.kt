package com.alexc.movielistapp.core.moviedetails.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.core.moviedetails.MovieDetailsViewModel
import com.alexc.movielistapp.core.watchlist.WatchlistViewModel
import com.alexc.movielistapp.data.model.MovieDetails
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.favourites.FavoritesViewModel
import com.alexc.movielistapp.ui.theme.OpenSans


@Composable
 fun MovieDescription(
    movieItem:MovieDetails,
    navController: NavController,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    watchlistViewModel: WatchlistViewModel = hiltViewModel()


) {
    val coroutineScope = rememberCoroutineScope()

    Spacer(modifier = Modifier.height(10.dp))

    Text(
        movieItem.title,
        fontSize = 23.sp,
        color = MaterialTheme.colors.primary,
        fontFamily = OpenSans,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        textAlign = TextAlign.Center,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 8.dp)
    ) {
        Button(
            onClick = {
                favoritesViewModel.addToFavorites(movieItem)
                navController.navigate("favorites_screen")
            }
        ) {
            Text(text = "Add to Favorites")
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = {
                watchlistViewModel.addToWatchlist(movieItem)
                navController.navigate("watchlist_screen")
            }
        ) {
            Text("Add to Watchlist")
        }
    }

var str:String=""
    if(movieItem.genres.isNotEmpty()) {
        for (i in movieItem.genres) {
            if (i != null)
                str = str + i.name + ","
        }
    }
    
    Text(
        movieItem.homepage + "  .  " + str+ "  .  " + movieItem.runtime,
        fontSize = 12.sp,
        color = MaterialTheme.colors.primary,
        fontFamily = OpenSans,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        textAlign = TextAlign.Center,
    )

    if (movieItem.vote_average.toString().isNotEmpty()) {
        val rating = (movieItem.vote_average.toFloat() * 5) / 10

        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            RatingBar(
                rating = rating,
                modifier = Modifier
                    .height(20.dp)

            )
        }
    }

    Text(
        movieItem.overview,
        fontSize = 12.sp,
        color = MaterialTheme.colors.primary,
        fontFamily = OpenSans,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        textAlign = TextAlign.Center,
    )

    Spacer(modifier = Modifier.height(20.dp))

    ActorsList(navController = navController, actorsList = movieItem.production_companies)

    Spacer(modifier = Modifier.height(20.dp))


    var list= produceState<Resource<List<Result>>>(initialValue = Resource.Loading()) {
        if(movieItem.genres.isNotEmpty())
        value = viewModel.SimilarMovie(movieItem.genres[0].id.toString())
    }.value
    Log.d("ANIKET",list.toString())
     if(list.data!=null)
  SimilarMoviesList(navController = navController,list.data)

    Spacer(modifier = Modifier.height(30.dp))

}



