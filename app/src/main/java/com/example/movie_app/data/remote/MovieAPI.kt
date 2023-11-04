package com.example.movie_app.data.remote

import com.example.movie_app.data.remote.dto.MovieDetailDTO
import com.example.movie_app.data.remote.dto.MoviesDTO
import com.example.movie_app.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET(".")
    suspend fun getMovies(
        @Query("s") search: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MoviesDTO

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MovieDetailDTO
}