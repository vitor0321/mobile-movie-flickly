package com.walcker.flickly.products.movies.features.ui.features.movieDetails

import com.walcker.flickly.products.movies.features.domain.models.Movie
import com.walcker.flickly.products.movies.strings.features.MovieDetailString

internal data class MovieDetailsState(
    val loading: Boolean = true,
    val string: MovieDetailString = MovieDetailString(),
    val movie: Movie? = null,
    val errorMessage: String? = null,
)