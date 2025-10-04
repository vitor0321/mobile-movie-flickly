package com.walcker.movies.produto.movies.features.ui.di

import com.walcker.movies.produto.movies.features.ui.features.movieDetails.MovieDetailsStepModel
import com.walcker.movies.produto.movies.features.ui.features.movies.MoviesListStepModel
import org.koin.dsl.module

internal val stepModelModule = module {
    factory { MoviesListStepModel(moviesRepository = get()) }
    factory { MovieDetailsStepModel(moviesRepository = get()) }
}