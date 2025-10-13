package com.walcker.movies.produto.movies.features.ui.features.home

import com.walcker.movies.produto.movies.features.domain.models.MovieSection
import kotlinx.collections.immutable.ImmutableList

internal sealed interface HomeMoviesState {
    data object Loading : HomeMoviesState
    data class Success(val movies: ImmutableList<MovieSection>) : HomeMoviesState
    data class Error(val message: String) : HomeMoviesState
}