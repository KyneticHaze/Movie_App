package com.example.movie_app.domain.use_case.get_movies

import com.example.movie_app.data.remote.dto.transformToMovie
import com.example.movie_app.domain.model.Movie
import com.example.movie_app.domain.repository.MovieRepository
import com.example.movie_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun executeGetMovies(search: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)

            if (movieList.response == "True") {
                emit(Resource.Success(movieList.transformToMovie()))
            } else {
                emit(Resource.Error("No Movie Found"))
            }
        } catch (e: IOError) {
            emit(Resource.Error("No internet connection"))
        }
    }
}