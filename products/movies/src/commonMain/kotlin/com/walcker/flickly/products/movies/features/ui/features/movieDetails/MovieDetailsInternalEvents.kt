package com.walcker.flickly.products.movies.features.ui.features.movieDetails


internal interface MovieDetailsInternalEvents {
    data class OnError(val errorMessage: String) : MovieDetailsInternalEvents
}