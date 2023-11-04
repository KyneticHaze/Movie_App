package com.example.movie_app.presentation.movie_list.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movie_app.presentation.Routes
import com.example.movie_app.presentation.movie_list.MoviesEvent
import com.example.movie_app.presentation.movie_list.components.MovieListRow
import com.example.movie_app.presentation.movie_list.components.MoviesSearchBar
import com.example.movie_app.presentation.movie_list.viewodel.MoviesViewModel

@Composable
fun MoviesScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column {
            MoviesSearchBar(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                hint = "Search Movie...",
            ) {
                viewModel.onEvent(MoviesEvent.Search(it))
            }

            LazyColumn(Modifier.fillMaxSize()) {
                items(state.movies) {movie ->
                    MovieListRow(movie = movie) {
                        navController.navigate(Routes.MovieDetailScreen.route + "/${movie.imdbID}")
                    }
                }
            }
        }
    }
}