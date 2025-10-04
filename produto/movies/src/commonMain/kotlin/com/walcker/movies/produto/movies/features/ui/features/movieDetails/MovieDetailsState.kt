package com.walcker.movies.produto.movies.features.ui.features.movieDetails

import com.walcker.movies.produto.movies.features.domain.models.Movie

internal sealed interface MovieDetailsState {
    data object Loading : MovieDetailsState
    data class Success(val movie: Movie) : MovieDetailsState
    data class Error(val message: String) : MovieDetailsState
}