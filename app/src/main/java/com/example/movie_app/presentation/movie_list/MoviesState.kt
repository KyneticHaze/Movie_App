package com.example.movie_app.presentation.movie_list

import com.example.movie_app.domain.model.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val search: String = "Batman"
)