package com.walcker.flickly.products.movies.features.ui.features.home

import com.walcker.flickly.products.movies.features.domain.models.MovieSection
import com.walcker.flickly.products.movies.strings.MoviesListStrings
import kotlinx.collections.immutable.ImmutableList

internal data class HomeMoviesState(
    val loading: Boolean = true,
    val string: MoviesListStrings = MoviesListStrings(),
    val movies: ImmutableList<MovieSection>? = null,
)