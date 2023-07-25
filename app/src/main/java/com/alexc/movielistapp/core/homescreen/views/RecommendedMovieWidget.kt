package com.alexc.movielistapp.core.homescreen.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.alexc.movielistapp.core.ForYou.ForYouViewModel
import com.alexc.movielistapp.core.homescreen.HomeScreenViewModel
import com.alexc.movielistapp.data.model.MovieDetails
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.ui.theme.OpenSans
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun RecommendedMovieWidget(
    navController: NavController,
    viewModel: ForYouViewModel,
    context: Context
) {
    // Observe the recommendedMovies list from the viewModel
    val recommendedMovies by viewModel.recommendedMovies.observeAsState(emptyList())

    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            "AI Recommendations",
            fontSize = 23.sp,
            color = MaterialTheme.colors.primary,
            fontFamily = OpenSans,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 7.dp)
        )
        IconButton(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                tint = MaterialTheme.colors.primary
            )
        }
    }

    Spacer(modifier = Modifier.height(10.dp))

    if (recommendedMovies.isEmpty()) {
        Text(
            "There is no movie in your favorites, please add your favorite movies to get AI recommendations!",
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(16.dp)
        )
    } else {
        LazyRow {
            items(recommendedMovies) { movie ->
                MovieCard(movie = movie, navController = navController)
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text("AI Recommendations")
            },
            text = {
                Text(
                    "Based on the movies in your favorites, this will be AI recommendations to watch the next movie!",
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}
