package com.walcker.flickly.products.movies.features.ui.features.movieDetails

internal interface MovieDetailsInternalRoute {
    data class OnMovieDetailsData(val movieId: Int) : MovieDetailsInternalRoute
}