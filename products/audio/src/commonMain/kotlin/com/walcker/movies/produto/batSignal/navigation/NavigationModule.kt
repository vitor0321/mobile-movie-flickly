package com.walcker.movies.produto.batSignal.navigation

import com.walcker.movies.navigator.BatSignalEntry
import org.koin.dsl.module

internal val navigationModule = module {
    single<BatSignalEntry> { AudioEntryImpl() }
}