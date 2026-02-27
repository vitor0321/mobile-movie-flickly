package com.walcker.flickly.products.movies.features.ui.di

import com.walcker.flickly.navigator.AudioDestination
import com.walcker.flickly.navigator.MoviesDestination
import com.walcker.flickly.core.domain.setting.PasswordSettings
import com.walcker.flickly.products.movies.features.ui.features.home.HomeMoviesStepModel
import com.walcker.flickly.products.movies.features.ui.features.movieDetails.MovieDetailsStepModel
import org.koin.dsl.module

internal val stepModelModule = module {
    factory {
        HomeMoviesStepModel(
            moviesRepository = get(),
            navigatorHolder = get(),
            stringsHolder = get(),
            moviesDestination = get<MoviesDestination>(),
            audioDestination = get<AudioDestination>(),
            passwordSettings = get<PasswordSettings>(),
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