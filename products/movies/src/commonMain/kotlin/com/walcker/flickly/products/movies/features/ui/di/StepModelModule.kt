package com.walcker.flickly.products.movies.features.ui.di

import com.walcker.flickly.navigator.AudioEntry
import com.walcker.flickly.navigator.MoviesEntry
import com.walcker.flickly.products.movies.features.ui.features.home.HomeMoviesStepModel
import com.walcker.flickly.products.movies.features.ui.features.movieDetails.MovieDetailsStepModel
import org.koin.dsl.module

internal val stepModelModule = module {
    factory {
        HomeMoviesStepModel(
            moviesRepository = get(),
            navigatorHolder = get(),
            stringsHolder = get(),
            moviesEntry = get<MoviesEntry>(),
            audioEntry = get<AudioEntry>(),
        )
    }
    factory {
        MovieDetailsStepModel(
            moviesRepository = get(),
            stringsHolder = get(),
            navigatorHolder = get(),
        )
    }
}