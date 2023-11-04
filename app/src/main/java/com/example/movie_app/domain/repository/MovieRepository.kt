package com.example.movie_app.domain.repository

import com.example.movie_app.data.remote.dto.MovieDetailDTO
import com.example.movie_app.data.remote.dto.MoviesDTO


interface MovieRepository {

    suspend fun getMovies(search: String): MoviesDTO

    suspend fun getMovieDetail(imdbId: String): MovieDetailDTO
}