package com.alexc.movielistapp.core.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.movielistapp.core.bottombar.BottomBar
import com.alexc.movielistapp.core.search.views.SearchInfoView
import com.alexc.movielistapp.core.search.views.SearchResultCard


@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = Modifier
            .fillMaxSize()
    ) {

        val state = viewModel.state

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            OutlinedTextField(
                value = state.searchTerm,
                onValueChange = {
                    viewModel.onSearch(it)
                },
                trailingIcon = {
                    if (state.searchTerm.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "Clear",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.clickable {
                                viewModel.onSearch("")
                            }
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                placeholder = {
                    Text(text = "Search...")
                },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(CenterHorizontally),
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.surface)
            )

            LazyColumn {
                items(state.movies.size) {
                    SearchResultCard(navController = navController, movie = state.movies[it])
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-60).dp)
            ) {
                when {
                    state.isLoading -> {
                        CircularProgressIndicator(color = MaterialTheme.colors.primary)
                    }
                    state.isError -> {
                        SearchInfoView()
                    }
                    state.movies.isEmpty() -> {
                        if (state.searchTerm.isEmpty()) {
                            SearchInfoView(
                                icon = Icons.Rounded.Search,
                                message = "Try to type something"
                            )
                        } else {
                            SearchInfoView(message = "No movies found")
                        }
                    }
                }
            }
        }
    }
}