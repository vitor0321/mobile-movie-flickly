package com.walcker.movies.produto.movies.features.ui.features.movies

import com.walcker.movies.produto.movies.features.domain.models.MovieSection
import kotlinx.collections.immutable.ImmutableList

internal sealed interface MoviesListState {
    data object Loading : MoviesListState
    data class Success(val movies: ImmutableList<MovieSection>) : MoviesListState
    data class Error(val message: String) : MoviesListState
}