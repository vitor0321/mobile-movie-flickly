package com.walcker.flickly.products.movies.features.ui.features.home

internal interface HomeMoviesInternalEvents {
    data class OnError(val errorMessage: String) : HomeMoviesInternalEvents
}