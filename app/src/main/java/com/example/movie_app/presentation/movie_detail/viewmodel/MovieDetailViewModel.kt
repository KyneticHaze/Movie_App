package com.example.movie_app.presentation.movie_detail.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.domain.use_case.get_movie_detail.GetMovieDetailsUseCase
import com.example.movie_app.presentation.movie_detail.MovieDetailState
import com.example.movie_app.util.Constants.IMDB_ID
import com.example.movie_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        stateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdbId: String) {
        getMovieDetailsUseCase.executeGetMovieDetails(imdbId)
            .onEach {
                when (it) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            movieDetail = it.data
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = it.message ?: "Error!"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}