package com.walcker.flickly.products.movies.navigation

import cafe.adriel.voyager.core.screen.Screen
import com.walcker.flickly.navigator.MoviesEntry
import com.walcker.flickly.products.movies.features.ui.features.home.HomeMoviesStep
import com.walcker.flickly.products.movies.features.ui.features.movieDetails.MovieDetailStep

internal class MoviesEntryImpl : MoviesEntry {

    override fun moviesHome(): Screen = HomeMoviesStep

    override fun movieDetails(movieId: String): Screen = MovieDetailStep(movieId.toInt())
}