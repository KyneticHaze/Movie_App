package com.example.movie_app.data.remote.dto

import com.example.movie_app.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MoviesDTO(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val search: List<Search>,
    val totalResults: String
)

fun MoviesDTO.transformToMovie(): List<Movie> {
    return search.map { search ->
        Movie(search.poster, search.title, search.year, search.imdbID)
    }
}