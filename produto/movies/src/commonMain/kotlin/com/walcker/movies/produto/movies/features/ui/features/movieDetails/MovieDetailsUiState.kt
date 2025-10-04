package com.walcker.movies.produto.movies.features.ui.features.movieDetails

import com.walcker.movies.produto.movies.features.domain.models.Movie

internal sealed interface MovieDetailsUiState {
    data object Loading : MovieDetailsUiState
    data class Success(val movie: Movie) : MovieDetailsUiState
    data class Error(val message: String) : MovieDetailsUiState
}