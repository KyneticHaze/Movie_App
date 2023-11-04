package com.example.movie_app.presentation.movie_list

sealed class MoviesEvent {
    data class Search(val search: String): MoviesEvent()
}