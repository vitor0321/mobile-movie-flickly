package com.walcker.flickly.products.movies.features.ui.features.movieDetails

import com.walcker.flickly.products.movies.features.domain.models.Movie
import com.walcker.flickly.products.movies.strings.MovieDetailStrings

internal data class MovieDetailsState(
    val loading: Boolean = true,
    val string: MovieDetailStrings = MovieDetailStrings(),
    val movie: Movie? = null,
)