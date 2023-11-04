package com.example.movie_app.presentation.movie_list.viewodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.domain.use_case.get_movies.GetMovieUseCase
import com.example.movie_app.presentation.movie_list.MoviesEvent
import com.example.movie_app.presentation.movie_list.MoviesState
import com.example.movie_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MoviesState()) // dosyanın içinde bununla işlemler yapacağız
    val state: State<MoviesState> =
        _state // Buradaki State compose runtime'dan gelen bir sınıf // dosyanın dışında yani view'da bunu kullanacak

    private var anyJob: Job? = null

    init {
        getMovies(_state.value.search)
    }

    private fun getMovies(search: String) {
        anyJob?.cancel()
        anyJob = getMovieUseCase.executeGetMovies(search)
            .onEach {
                // Her bir yayın için işlem
                when(it) {
                    is Resource.Success -> {
                        _state.value = MoviesState(movies = it.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = it.message ?: "Error"
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

    fun onEvent(event: MoviesEvent) {
        when(event) {
            is MoviesEvent.Search -> {
                getMovies(event.search)
            }
        }
    }
}