package com.walcker.flickly.products.movies.features.ui.features.movieDetails

import com.walcker.flickly.products.movies.features.domain.models.Movie

internal sealed interface MovieDetailsState {
    data object Loading : MovieDetailsState
    data class Success(val movie: Movie) : MovieDetailsState
    data class Error(val message: String) : MovieDetailsState
}