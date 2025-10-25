package com.walcker.flickly.products.movies.navigation

import com.walcker.flickly.navigator.MoviesEntry
import org.koin.dsl.module

internal val navigationModule = module {
    single<MoviesEntry> { MoviesEntryImpl() }
}