package com.alexc.movielistapp.core.moviedetails.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alexc.movielistapp.data.models.MovieItem
import com.alexc.movielistapp.ui.theme.OpenSans

@Composable
fun MovieDescription(
    movieItem: MovieItem,
    navController: NavController
) {

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
        // "Add to Favorite" button
        Button(
            onClick = { /* Handle "Add to Favorite" button click */ },
            modifier = Modifier.weight(1f)
        ) {
            Text("Add to Favorite")
        }

        Spacer(modifier = Modifier.width(8.dp))

        // "Add to Watchlist" button
        Button(
            onClick = { /* Handle "Add to Watchlist" button click */ },
            modifier = Modifier.weight(1f)
        ) {
            Text("Add to Watchlist")
        }
    }



    Text(
        movieItem.year + "  .  " + movieItem.genres + "  .  " + movieItem.runtimeStr,
        fontSize = 12.sp,
        color = MaterialTheme.colors.primary,
        fontFamily = OpenSans,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        textAlign = TextAlign.Center,
    )

    if (movieItem.imDbRating.isNotEmpty()) {
        val rating = (movieItem.imDbRating.toFloat() * 5) / 10

        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            RatingBar(
                rating = rating,
                modifier = Modifier
                    .height(20.dp)

            )
        }
    }

    Text(
        movieItem.plot,
        fontSize = 12.sp,
        color = MaterialTheme.colors.primary,
        fontFamily = OpenSans,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        textAlign = TextAlign.Center,
    )

    Spacer(modifier = Modifier.height(20.dp))

    ActorsList(navController = navController, actorsList = movieItem.actorsList)

    Spacer(modifier = Modifier.height(20.dp))

    SimilarMoviesList(navController = navController, movieItem.similarsList)

    Spacer(modifier = Modifier.height(30.dp))

}

