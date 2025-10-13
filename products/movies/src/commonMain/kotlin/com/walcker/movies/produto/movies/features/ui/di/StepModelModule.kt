package com.walcker.movies.produto.movies.features.ui.di

import com.walcker.movies.produto.movies.features.ui.features.movieDetails.MovieDetailsStepModel
import com.walcker.movies.produto.movies.features.ui.features.home.HomeMoviesStepModel
import org.koin.dsl.module

internal val stepModelModule = module {
    factory { HomeMoviesStepModel(moviesRepository = get()) }
    factory { MovieDetailsStepModel(moviesRepository = get()) }
}