package com.example.movie_app.presentation.movie_detail

import com.example.movie_app.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    val error: String = "",
    val search: String = "Batman"
    )
