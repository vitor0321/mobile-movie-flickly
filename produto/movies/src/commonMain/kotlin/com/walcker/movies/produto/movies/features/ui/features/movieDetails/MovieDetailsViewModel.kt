package com.walcker.movies.produto.movies.features.ui.features.movieDetails

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.walcker.movies.produto.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.produto.movies.handle.handleMessageError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MovieDetailsViewModel internal constructor(
    private val moviesRepository: MoviesRepository,
) : StateScreenModel<MovieDetailsUiState>(MovieDetailsUiState.Loading) {

    private val _uiState = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    internal val uiState = _uiState.asStateFlow()

    internal fun onEvent(onEvent: MovieDetailsInternalRoute) {
        when (onEvent) {
            is MovieDetailsInternalRoute.OnMovieDetailsData -> getMovieDetails(movieId = onEvent.movieId)
        }
    }

    private fun getMovieDetails(movieId: Int) {
        screenModelScope.launch {
            moviesRepository.getMovieDetail(movieId = movieId)
                .onSuccess { movie ->
                    _uiState.update { MovieDetailsUiState.Success(movie) }
                }
                .onFailure { error ->
                    _uiState.update { MovieDetailsUiState.Error(handleMessageError(exception = error)) }
                }
        }
    }
}