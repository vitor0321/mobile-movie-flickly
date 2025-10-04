package com.walcker.movies.produto.movies.features.ui.features.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

internal sealed class MoviesDestinations : ScreenProvider {
    object MoviesList : MoviesDestinations()
    data class MovieDetails(val movieId: Int) : MoviesDestinations()
}