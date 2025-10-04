package com.walcker.movies.produto.movies.features.ui.features.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.walcker.movies.produto.movies.features.ui.features.movieDetails.MovieDetailStep
import com.walcker.movies.produto.movies.features.ui.features.movies.MoviesListStep

public val featurePostsScreenModule = screenModule {
    register<MoviesDestinations.MoviesList> {
        MoviesListStep
    }
    register<MoviesDestinations.MovieDetails> { provider ->
        MovieDetailStep(movieId = provider.movieId)
    }
}