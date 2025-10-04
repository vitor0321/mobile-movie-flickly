package com.walcker.movies.produto.movies.features.ui.features.movies

import com.walcker.movies.produto.movies.features.domain.models.MovieSection
import kotlinx.collections.immutable.ImmutableList

internal sealed interface MoviesListUiState {
    data object Loading : MoviesListUiState
    data class Success(val movies: ImmutableList<MovieSection>) : MoviesListUiState
    data class Error(val message: String) : MoviesListUiState
}