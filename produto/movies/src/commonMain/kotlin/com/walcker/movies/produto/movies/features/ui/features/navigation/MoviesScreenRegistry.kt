package com.walcker.movies.produto.movies.features.ui.features.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.walcker.movies.produto.movies.features.ui.features.movieDetails.MovieDetailScreen
import com.walcker.movies.produto.movies.features.ui.features.movies.MoviesListScreen

public val featurePostsScreenModule = screenModule {
    register<MoviesDestinations.MoviesList> {
        MoviesListScreen
    }
    register<MoviesDestinations.MovieDetails> { provider ->
        MovieDetailScreen(movieId = provider.movieId)
    }
}