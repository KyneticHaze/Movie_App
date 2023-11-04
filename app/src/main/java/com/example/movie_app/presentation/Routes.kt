package com.example.movie_app.presentation

sealed class Routes(val route: String) {
    data object MoviesScreen: Routes("movies_screen")
    data object MovieDetailScreen: Routes("movie_detail_screen")
}
