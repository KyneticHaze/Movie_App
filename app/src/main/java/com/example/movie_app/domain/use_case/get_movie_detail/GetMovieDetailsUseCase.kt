package com.example.movie_app.domain.use_case.get_movie_detail

import com.example.movie_app.data.remote.dto.transformToMovieDetail
import com.example.movie_app.domain.model.MovieDetail
import com.example.movie_app.domain.repository.MovieRepository
import com.example.movie_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun executeGetMovieDetails(imdbId: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId)

            if (movieDetail.response == "True") {
                emit(Resource.Success(movieDetail.transformToMovieDetail()))
            } else {
                emit(Resource.Error("No Movie Found"))
            }
        } catch (e: IOError) {
            emit(Resource.Error("No internet connection"))
        }
    }
}