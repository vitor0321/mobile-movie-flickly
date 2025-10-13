package com.walcker.movies.produto.movies.navigation

import cafe.adriel.voyager.core.screen.Screen
import com.walcker.movies.navigator.MoviesEntry
import com.walcker.movies.produto.movies.features.ui.features.movieDetails.MovieDetailStep
import com.walcker.movies.produto.movies.features.ui.features.home.HomeMoviesStep

internal class MoviesEntryImpl : MoviesEntry {

    override fun moviesHome(): Screen = HomeMoviesStep

    override fun movieDetails(movieId: String): Screen = MovieDetailStep(movieId.toInt())
}