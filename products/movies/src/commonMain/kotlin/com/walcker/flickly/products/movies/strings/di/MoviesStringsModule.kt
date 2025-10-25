package com.walcker.flickly.products.movies.strings.di

import com.walcker.flickly.products.movies.strings.MoviesStringsHolder
import org.koin.dsl.module

internal val moviesStringsModule = module {
    single { MoviesStringsHolder() }
}

