package com.walcker.movies.produto.movies.navigation

import com.walcker.movies.navigator.MoviesEntry
import org.koin.dsl.module

internal val navigationModule = module {
    single<MoviesEntry> { MoviesEntryImpl() }
}