package com.alexc.movielistapp.core.homescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.ui.theme.OpenSans
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            item {
                MostPopularMoviesWidget(navController)
            }
            item {
                CategoriesWidget(listOf("Action", "Adventure", "Comedy"), navController)
            }
        }
    }
}


@Composable
fun MostPopularMoviesWidget(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val movieList by remember { viewModel.mostPopularMoviesList }

    Surface(
        modifier = Modifier
            .padding(start = 10.dp, end = 0.dp)
    ) {
        Column {
            Text(
                "Most popular",
                fontSize = 23.sp,
                color = MaterialTheme.colors.primary,
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 7.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow {
                items(movieList.size) {
                    movieCard(movie = movieList[it], navController = navController)
                }
            }
        }
    }
}

@Composable
fun movieCard(
    movie: MovieListItem,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Box(
        modifier = modifier
            .width(150.dp)
            .clickable {
                // TODO onClick
            }
            .padding(10.dp),
    ) {
        Column(horizontalAlignment = CenterHorizontally) {
            Image(
                painter = rememberCoilPainter(request = movie.image),
                contentDescription = movie.title,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                movie.fullTitle,
                fontFamily = OpenSans,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesWidget(
    categories: List<String>,
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Column {

            Text(
                "Categories",
                fontSize = 23.sp,
                color = MaterialTheme.colors.primary,
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 7.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            FlowRow(
                mainAxisSize = SizeMode.Expand,
                mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
            ) {
                for (category in categories) {
                    CategoryItem(category = category, navController = navController)
                }
            }
        }
    }
}


@Composable
fun CategoryItem(category: String, navController: NavController) {
}