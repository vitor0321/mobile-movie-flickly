package com.walcker.flickly.products.movies.features.ui.di

import com.walcker.flickly.products.movies.features.ui.features.home.HomeMoviesStepModel
import com.walcker.flickly.products.movies.features.ui.features.movieDetails.MovieDetailsStepModel
import org.koin.dsl.module

internal val stepModelModule = module {
    factory { HomeMoviesStepModel(moviesRepository = get()) }
    factory { MovieDetailsStepModel(moviesRepository = get()) }
}