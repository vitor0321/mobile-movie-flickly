package com.walcker.movies.produto.movies.features.ui.di

import com.walcker.movies.produto.movies.features.ui.features.movieDetails.MovieDetailsViewModel
import com.walcker.movies.produto.movies.features.ui.features.movies.MoviesListViewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    factory { MoviesListViewModel(moviesRepository = get()) }
    factory { MovieDetailsViewModel(moviesRepository = get()) }
}